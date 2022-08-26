/**
 * 
 */
package org.openrouteservice.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.openrouteservice.client.model.OrsLocation;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Interceptor.Chain;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Converter.Factory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jsgranier
 * 10 août 2022
 */
public class OrsServiceBuilder {
	
	private String rootUrl;
	private String apiToken;
	private OrsServices services;
	
	public OrsServiceBuilder() {
		super();
	}
	


	public OrsServiceBuilder setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
		return this;
	}




	public OrsServiceBuilder setApiToken(String apiToken) {
		this.apiToken = apiToken;
		return this;
	}




	public OrsServices create() {
		OkHttpClient client = getUnsafeOkHttpClient(apiToken);
		
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(OrsLocation.class, new JsonSerializer<OrsLocation>() {

			@Override
			public JsonElement serialize(OrsLocation src, Type typeOfSrc, JsonSerializationContext context) {
				JsonArray locationInArray = new JsonArray();
				locationInArray.add(src.getLongitude());
				locationInArray.add(src.getLatitude());
				
				return locationInArray;
			}
		});
		
		gb.registerTypeAdapter(OrsLocation.class, new JsonDeserializer<OrsLocation>() {

			@Override
			public OrsLocation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				if(json.isJsonArray() && json.getAsJsonArray().size() == 2) {
					JsonArray item = json.getAsJsonArray();
					OrsLocation loc = new OrsLocation(item.get(0).getAsDouble(), item.get(1).getAsDouble());
					return loc;
				} else 
					throw new JsonParseException("Attentu un tableau à deux valeurs pour décrire des coordonées GPS, mais j'ai autre chose à la place :  "+json.toString());
				
			}

		});
		
		Factory gf = GsonConverterFactory.create(gb.create());
		
		
		
		Retrofit retrofit = new Retrofit.Builder().baseUrl(rootUrl).client(client)
				.addConverterFactory(gf)
				/*
			    .addConverterFactory(
			    	    SimpleXmlConverterFactory.createNonStrict( new Persister(new AnnotationStrategy() ) // important part!
			    	    )
			    	)
			    	 .addConverterFactory(GsonConverterFactory.create())
			    	*/
			    .build();
		
		return retrofit.create(OrsServices.class);
	}
		
		
	private static OkHttpClient getUnsafeOkHttpClient(String apiToken) {
		  try {
		    // Create a trust manager that does not validate certificate chains
		    final TrustManager[] trustAllCerts = new TrustManager[] {
		        new X509TrustManager() {
		          @Override
		          public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		          }

		          @Override
		          public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		          }

		          @Override
		          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		            return new java.security.cert.X509Certificate[]{};
		          }
		        }
		    };

		    // Install the all-trusting trust manager
		    final SSLContext sslContext = SSLContext.getInstance("SSL");
		    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		    // Create an ssl socket factory with our all-trusting manager
		    final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

		    OkHttpClient.Builder builder = new OkHttpClient.Builder();
		    builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
		    builder.hostnameVerifier(new HostnameVerifier() {
		      @Override
		      public boolean verify(String hostname, SSLSession session) {
		        return true;
		      }
		    });
		    
		    builder.addInterceptor(new Interceptor() {
			
				@Override
				public okhttp3.Response intercept(Chain chain) throws IOException {
					Request originalRequest = chain.request();
	
		            Request.Builder builder = originalRequest.newBuilder();
		            
		            builder = builder.addHeader("Authorization", apiToken);
		            
		            //System.out.println(originalRequest.url());
	
		            Request newRequest = builder.build();
		            
		            if(newRequest.body() != null) {
		            	Buffer b = new Buffer();
		            	newRequest.body().writeTo(b);
		            	b.copyTo(System.out);
		            	System.out.println();
		            }
		            
		            
		            return chain.proceed(newRequest);
				}
			});
		    
		    builder.connectTimeout(5, TimeUnit.SECONDS);
		    builder.readTimeout(120, TimeUnit.SECONDS);

		    OkHttpClient okHttpClient = builder.build();
		    return okHttpClient;
		  } catch (Exception e) {
		    throw new RuntimeException(e);
		  }
		}
	
}

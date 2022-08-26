import java.io.IOException;

import org.openrouteservice.client.OptimizationServiceRequestBody;
import org.openrouteservice.client.OptimizationServiceResponse;
import org.openrouteservice.client.OrsServices;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import retrofit2.Response;

/**
 * 
 */

/**
 * @author jsgranier
 * 25 août 2022
 */
public class OsrException extends Exception {

	/**
	 * @param service
	 * @param params
	 * @param error
	 */
	public OsrException(String serviceUrl, Object params, String error) {
		super("Erreur remontée par l'API distante : '"+error+"'. Appelé : "+serviceUrl+" avec "+new GsonBuilder().create().toJson(params));
		
	}

	/**
	 * @param tempsResponse
	 * @throws OsrException 
	 */
	public static void throwIfUnsucessfull(Response rep, String service, Object params) throws OsrException {
		if( ! rep.isSuccessful()) {
			
			try {
				JsonObject jerr = new GsonBuilder().create().fromJson(rep.errorBody().string(), JsonObject.class);
				throw new OsrException(service, params, jerr.get("error").getAsString() );
			} catch (NullPointerException | JsonSyntaxException e) {
				try {
					throw new OsrException(service, params, "L'API a renvoyé une erreur, et il n'a pas été possible de décoder son message JSON. "+rep.errorBody().string());
				} catch (IOException e1) {
					throw new OsrException(service, params, "L'API a renvoyé une erreur, et il semble impossible de lire le body d'erreur. ");
				}
			} catch (IOException e) {
				throw new OsrException(service, params, "L'API a renvoyé une erreur, et il semble impossible de lire le body d'erreur. ");
			}
			
			
		}
		
	}

}

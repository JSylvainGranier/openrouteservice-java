import java.io.IOException;

import org.openrouteservice.client.DirectionServiceRequest;
import org.openrouteservice.client.DirectionServiceResponse;
import org.openrouteservice.client.OptimizationServiceRequestBody;
import org.openrouteservice.client.OptimizationServiceResponse;
import org.openrouteservice.client.OrsServiceBuilder;
import org.openrouteservice.client.OrsServices;
import org.openrouteservice.client.model.Job;
import org.openrouteservice.client.model.OrsLocation;
import org.openrouteservice.client.model.Vehicle;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 
 */

/**
 * @author jsgranier
 * 10 août 2022
 */
public class OsrApiClientTest {
	public static void main(String[] args) throws IOException, OsrException {
		
		String rootUrl = "https://api.openrouteservice.org/";
		
		OrsServices service =  new OrsServiceBuilder()
			.setApiToken("5b3ce3597851110001cf6248decb6d7745844801973481477353581f")
			.setRootUrl(rootUrl)
			.create();
		
		
		OptimizationServiceRequestBody params = new OptimizationServiceRequestBody();
		
		
		//Attention, faut renverser pour Google Maps et OSM
		
		//Nimes : 4,38593136943993  ,  43,8230746019966
		//Pichegu : 4,458808542127  ,  43,7173376600996
		//VDR : 2,91461765192929  ,  42,6305108674444
		
		//Forage Conquerac : 3,91708113169166  ,  43,94675513438
		//Forage de Bouillargues : 4,429424  ,  43,78980983
		//Station de pompage de Claret : 3,87766298  ,  43,8607057099999
		
		
		params.addJob(
				new Job(1, new OrsLocation(43.8230746019966, 4.38593136943993)));
		
		params.addJob(
				new Job(2, new OrsLocation(43.8607057099999, 3.87766298)));
		
		params.addJob(
				new Job(2, new OrsLocation( 43.78980983, 4.429424)));
		
		params.addVehicule(
				new Vehicle(44, new OrsLocation(43.7173376600996, 4.458808542127), new OrsLocation(42.63051086744447, 2.9146176519292) )
				);
		
		Call<OptimizationServiceResponse> optimizeCaller = service.optimise(params);
		
		Response<OptimizationServiceResponse> tempsResponse = optimizeCaller.execute();
		
		
		OsrException.throwIfUnsucessfull(tempsResponse, "optimize", params);
		
		OptimizationServiceResponse reponse = tempsResponse.body();
		
		System.out.println("REP="+new GsonBuilder().create().toJson(reponse));
		
		/*
		DirectionServiceRequest dirRequest = new DirectionServiceRequest();
		dirRequest.addCoordinates(new OrsLocation(43.7173376600996, 4.458808542127));
		
		dirRequest.addCoordinates(new OrsLocation(43.8230746019966, 4.38593136943993));
		dirRequest.addCoordinates(new OrsLocation(43.8607057099999, 3.87766298));
		dirRequest.addCoordinates(new OrsLocation( 43.78980983, 4.429424));
		
		
		dirRequest.addCoordinates( new OrsLocation(42.63051086744447, 2.9146176519292));
		dirRequest.radiuses = 5000;
		
		Call<DirectionServiceResponse> calDirection = service.directionForACar(dirRequest);
		
		Response<DirectionServiceResponse> repDirection = calDirection.execute();
		
		OsrException.throwIfUnsucessfull(repDirection, rootUrl, "direction");
		
		System.out.println("REP="+new GsonBuilder().create().toJson(repDirection.body()));
		*/
	}
}

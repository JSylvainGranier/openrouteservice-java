/**
 * 
 */
package org.openrouteservice.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author jsgranier
 * 10 août 2022
 */
public interface OrsServices {
	@POST("./optimization")
	public Call<OptimizationServiceResponse> optimise(@Body OptimizationServiceRequestBody  rq);
	
	@POST("./v2/directions/driving-car/json")
	public Call<DirectionServiceResponse> directionForACar(@Body DirectionServiceRequest  rq);
	
	
}


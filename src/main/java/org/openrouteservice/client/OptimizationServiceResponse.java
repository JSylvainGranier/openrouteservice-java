/**
 * 
 */
package org.openrouteservice.client;

import java.util.ArrayList;

import org.openrouteservice.client.model.Job;
import org.openrouteservice.client.model.Route;
import org.openrouteservice.client.model.Summary;


/**
 * @author jsgranier
 * 10 août 2022
 */
public class OptimizationServiceResponse {
	public int code;
    public Summary summary;
    public ArrayList<Job> unassigned;
    public ArrayList<Route> routes;
    
    
}

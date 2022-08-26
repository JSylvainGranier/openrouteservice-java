/**
 * 
 */
package org.openrouteservice.client;

import java.util.ArrayList;

import org.openrouteservice.client.model.Job;
import org.openrouteservice.client.model.Matrice;
import org.openrouteservice.client.model.Shipment;
import org.openrouteservice.client.model.Vehicle;


/**
 * @author jsgranier
 * 10 août 2022
 */

public class OptimizationServiceRequestBody {
	public ArrayList<Job> jobs = new ArrayList<>();
	public ArrayList<Shipment> shipments = new ArrayList<>();
    public ArrayList<Vehicle> vehicles = new ArrayList<>();
    public ArrayList<Matrice> matrices = new ArrayList<>();
	/**
	 * @param job
	 */
	public void addJob(Job job) {
		jobs.add(job);
	}
	
	public void addVehicule(Vehicle v) {
		vehicles.add(v);
	}
}



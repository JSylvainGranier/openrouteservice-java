/**
 * 
 */
package org.openrouteservice.client;

import java.util.ArrayList;

import org.openrouteservice.client.model.OrsLocation;

/**
 * @author jsgranier
 * 25 août 2022
 */
public class DirectionServiceRequest {
	public ArrayList<OrsLocation> coordinates = new ArrayList<>();
	public int radiuses;
	
	public void addCoordinates(OrsLocation loc) {
		this.coordinates.add(loc);
	}
}

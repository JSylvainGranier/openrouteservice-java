/**
 * 
 */
package org.openrouteservice.client.model;

/**
 * @author jsgranier
 * 10 août 2022
 */
public class OrsLocation {
	public double latitude;
	public double longitude;
	
	
	
	
	public OrsLocation(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	
}

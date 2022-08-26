/**
 * 
 */
package org.openrouteservice.client.model;

/**
 * @author jsgranier
 * 10 août 2022
 */
public class Vehicle{
    public int id;
    public String profile;
    public OrsLocation start; //Location
    public OrsLocation end; //Location
    public int[] capacity;
    public int[] skills;
    public int[] time_window;
    
	public Vehicle(int id,  OrsLocation start, OrsLocation end) {
		super();
		this.id = id;
		this.profile = "driving-car";
		this.start = start;
		this.end = end;
		
		capacity = new int[] {4};
		//skills = new int[] {1,14};
		//time_window = new int[] {1,14};


		
	}
    
    
    
}








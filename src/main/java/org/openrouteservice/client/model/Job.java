/**
 * 
 */
package org.openrouteservice.client.model;

import java.util.ArrayList;

/**
 * @author jsgranier
 * 10 août 2022
 */
public class Job{
    public int id;
    public String description;
    /**
     * Job setup duration (default 0) 
     */
    public int setup = 0;
    /**
     * Job service Duration (default to 0)
     */
    public int service = 0;
    /**
     * an array of integers describing multidimensional quantities
     */
    @Deprecated
    public ArrayList<Integer> amount;
    /**
     * an array of integers describing multidimensional quantities for pickup
     */
    public ArrayList<Integer> pickup;
    /**
     * an array of integers describing multidimensional quantities for delivery
     */
    public ArrayList<Integer> delivery;
    public OrsLocation location;  
    
    /**
     * index of relevant row and column in custom matrices
     */
    public int locationIndex;
    /**
     * an array of integers defining mandatory skills
     */
    public ArrayList<Integer>  skills;
    
    public ArrayList<TimeWindow> timeWindows;
    
    /**
     *  an integer in the [0, 100] range describing priority level (defaults to 0)
     */
    public int priority = 0;
    
	public Job(int id, OrsLocation location) {
		super();
		this.id = id;
		this.location = location;
	} 
    
    
 /*
	
	[time_windows]	an array of time_window objects describing valid slots for job service start
	*/
}
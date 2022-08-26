/**
 * 
 */
package org.openrouteservice.client.model;

import java.util.ArrayList;

/**
 * @author jsgranier
 * 10 août 2022
 */

public class Step{
    public String type;
    public OrsLocation location;
    public ArrayList<Integer> load;
    public int arrival;
    public double duration;
    public int id;
    public int service;
    public int waiting_time;
    public int job;
}

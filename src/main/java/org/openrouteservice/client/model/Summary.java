/**
 * 
 */
package org.openrouteservice.client.model;

import java.util.ArrayList;


/**
 * @author jsgranier
 * 10 août 2022
 */
public class Summary{
    public int cost;
    public int unassigned;
    public ArrayList<Integer> delivery;
    public ArrayList<Integer> amount;
    public ArrayList<Integer> pickup;
    public int service;
    public double duration;
    public double distance;
    public int waiting_time;
}

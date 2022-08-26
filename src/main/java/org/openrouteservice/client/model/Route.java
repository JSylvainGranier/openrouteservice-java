/**
 * 
 */
package org.openrouteservice.client.model;

import java.util.ArrayList;


/**
 * @author jsgranier
 * 10 août 2022
 */


public class Route{
    public int vehicle;
    public int cost;
    public ArrayList<Integer> delivery;
    public ArrayList<Integer> amount;
    public ArrayList<Integer> pickup;
    public int service;
    public double duration;
    public int waiting_time;
    public ArrayList<Step> steps;
}

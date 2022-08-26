/**
 * 
 */
package org.openrouteservice.client.model;

import java.util.ArrayList;

/**
 * @author jsgranier
 * 10 août 2022
 */
public class Shipment {
	private ShipmentStep pickup;
	private ShipmentStep delivery;
	/**
	 * multidimensional quantities
	 */
	private ArrayList<Integer> amount;
	/**
	 * mandatory skills
	 */
	private ArrayList<Integer> skills;
	/**
	 * an integer in the [0, 100] range describing priority level (defaults to 0)
	 */
	private int priority = 0;
}

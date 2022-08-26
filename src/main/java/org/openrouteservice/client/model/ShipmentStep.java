/**
 * 
 */
package org.openrouteservice.client.model;

/**
 * @author jsgranier
 * 10 août 2022
 */
public class ShipmentStep extends Job{

	/**
	 * @param id
	 * @param location
	 */
	public ShipmentStep(int id, OrsLocation location) {
		super(id, location);
	}

}

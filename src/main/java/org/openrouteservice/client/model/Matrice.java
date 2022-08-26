/**
 * 
 */
package org.openrouteservice.client.model;

/**
 * @author jsgranier
 * 10 août 2022
 * The matrices object allows to input (non-empty) custom matrices for each vehicle profile. 
 * Each matrix is an array of arrays of unsigned integers filed under the profile key, then under:

	-durations for a custom travel-time matrix that will be used for all checks against timing constraints;
	-costs for a custom cost matrix that will be used within all route cost evaluations.
	
	If only the durations value is provided, it's implied that it should also be used for costs evaluations.
 */
public class Matrice {

}

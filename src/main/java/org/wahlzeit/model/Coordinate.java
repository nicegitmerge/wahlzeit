/**
 * 
 */
package org.wahlzeit.model;

/**
 * 
 */
public interface Coordinate {
	public CartesianCoordinate asCartesianCoordinate();
	public double getCartesianDistance(Coordinate other);
	public SphericCoordinate asSphericCoordinate();
	public double getSphericDistance(Coordinate other);
	public double getDistance(Coordinate other);
	public boolean isEqual(Coordinate other);
}

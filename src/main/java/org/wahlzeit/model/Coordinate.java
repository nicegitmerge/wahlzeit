package org.wahlzeit.model;

/**
 * 
 */
public interface Coordinate {
	
	/**
	 * @methodtype conversion
	 */
	public CartesianCoordinate asCartesianCoordinate();
	
	/**
	 * @methodtype get
	 */
	public double getCartesianDistance(Coordinate other);
	
	/**
	 * @methodtype conversion
	 */
	public SphericCoordinate asSphericCoordinate();
	
	/**
	 * @methodtype get
	 */
	public double getSphericDistance(Coordinate other);
	
	/**
	 * @methodtype get
	 */
	public double getDistance(Coordinate other);
	
	/**
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate other);
}

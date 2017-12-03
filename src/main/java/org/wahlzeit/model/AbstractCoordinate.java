package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	
	public static final double EPSILON = 1e-6;
	
	
	public double getDistance(Coordinate other) {
		assertClassInvariants();
		assertNotNull(other);
		return getCartesianDistance(other);
	}
	
	/**
	 * @methodtype boolean query
	 */
	public boolean equals(Object obj) {
		assertClassInvariants();
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Coordinate)) return false;
		return isEqual((Coordinate)obj);
	}
	
	public boolean isEqual(Coordinate other) {
		assertClassInvariants();
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		return doIsEqual(other);
	}
	
	public double getCartesianDistance(Coordinate other) {
		assertClassInvariants();
		assertNotNull(other);
		return doGetCartesianDistance(other.asCartesianCoordinate());
	}
	
	public double getSphericDistance(Coordinate other) {
		assertClassInvariants();
		assertNotNull(other);
		return doGetSphericDistance(other.asSphericCoordinate());
	}
	
	/**
	 * @methodtype assert
	 */
	protected void assertDoubleVal(double d) {
		if (d == Double.NaN) throw new IllegalStateException();
		if (d == Double.NEGATIVE_INFINITY) throw new IllegalStateException();
		if (d == Double.POSITIVE_INFINITY) throw new IllegalStateException();
	}
	
	/**
	 * @methodtype assert
	 */
	protected void assertNotNull(Object o) {
		if (o == null) throw new IllegalArgumentException();
	}
	
	//interface
	public abstract CartesianCoordinate asCartesianCoordinate();
	public abstract SphericCoordinate asSphericCoordinate();
	
	//inheritance interface
	protected abstract boolean doIsEqual(Coordinate other);
	protected abstract double doGetCartesianDistance(CartesianCoordinate other);
	protected abstract double doGetSphericDistance(SphericCoordinate other);
	
	/**
	 * @methodtype assert
	 */
	protected abstract void assertClassInvariants();
}

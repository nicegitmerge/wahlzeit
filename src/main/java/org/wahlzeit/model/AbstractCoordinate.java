package org.wahlzeit.model;

import java.util.HashMap;

import org.wahlzeit.annotations.PatternInstance;

@PatternInstance(
		patternName = "Template Method",
		participants = {
				"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"
		}
	)
@PatternInstance(
		patternName = "Flyweight",
		participants = {
				"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"
		}
	)
public abstract class AbstractCoordinate implements Coordinate {
	
	public static final double EPSILON = 1e-6;
	
	/*
	 * value object impl
	 */
	protected static HashMap<Integer, Coordinate> allCoordinates = new HashMap<>();
	
	
	/**
	 * @methodtype get
	 */
	public double getDistance(Coordinate other) {
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
	
	@Override
	public int hashCode() {
		return toString().hashCode();
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
		if (d == Double.NaN) throw new IllegalArgumentException();
		if (d == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
		if (d == Double.POSITIVE_INFINITY) throw new IllegalArgumentException();
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

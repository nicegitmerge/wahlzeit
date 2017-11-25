package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	
	public static final double EPSILON = 1e-6;
	
	public double getDistance(Coordinate other) {
		assertNotNull(other);
		return getCartesianDistance(other);
	}
	
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Coordinate)) return false;
		return isEqual((Coordinate)obj);
	}
	
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		return doIsEqual(other);
	}
	
	protected abstract boolean doIsEqual(Coordinate other);
	
	protected void assertNotNull(Object o) {
		if (o == null) throw new IllegalArgumentException();
	}
}

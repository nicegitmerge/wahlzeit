package org.wahlzeit.model;

public class Coordinate {
	
	private double x, y, z;
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getDistance(Coordinate other) throws IllegalArgumentException {
		if (other == null) throw new IllegalArgumentException("Parameter null");
		double dx = other.x - x;
		double dy = other.y - y;
		double dz = other.z - z;
		return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		}
		if (other == this) return true;
		return (x == other.x && y == other.y && z == other.z);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Coordinate)) return false;
		return isEqual((Coordinate)obj);
	}
}

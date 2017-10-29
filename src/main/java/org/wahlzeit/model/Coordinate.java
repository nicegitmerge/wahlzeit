package org.wahlzeit.model;

public class Coordinate {
	
	private double x, y, z;
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getDistance(Coordinate other) {
		double dx = other.x - x;
		double dy = other.y - y;
		double dz = other.z - z;
		return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		}
		return (x == other.x && y == other.y && z == other.z);
	}
	
	@Override
	public boolean equals(Object obj) {
		return isEqual((Coordinate)obj);
	}
}

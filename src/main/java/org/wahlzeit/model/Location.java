package org.wahlzeit.model;

public class Location {
	
	public CartesianCoordinate coordinate;
	
	public Location(CartesianCoordinate c) throws IllegalArgumentException {
		if (c == null) throw new IllegalArgumentException("Coordinate null");
		coordinate = c;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Location)) return false;
		
		Location other = (Location)obj;
		if (coordinate == null && other.coordinate == null) {
			return true;
		}
		if (coordinate == null) {
			return false;
		}
		return coordinate.equals(other.coordinate);
	}
}

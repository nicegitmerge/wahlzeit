package org.wahlzeit.model;

public class Location {
	
	public Coordinate coordinate;
	
	public Location(Coordinate c) throws IllegalArgumentCheckedException {
		if (c == null) throw new IllegalArgumentCheckedException();
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

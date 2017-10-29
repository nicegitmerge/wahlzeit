package org.wahlzeit.model;

public class Location {
	
	public Coordinate coordinate;
	
	public Location(Coordinate c) {
		coordinate = c;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		Location other = (Location)obj;
		if (coordinate == null && other.coordinate == null) {
			return true;
		}
		if (coordinate == null || other.coordinate == null) {
			return false;
		}
		return coordinate.equals(other.coordinate);
	}
}

package org.wahlzeit.model;

public class Mountain {
	
	private static int idCounter = 0;
	
	private final int id;
	private MountainType type = null;
	private Location location = null;
	

	protected Mountain(MountainType mt) {
		type = mt;
		id = idCounter;
		idCounter++;
	}
	
	public void setLocation(Location loc) {
		if (loc == null) {
			throw new IllegalArgumentException();
		}
		location = loc;
	}
	
	public Location getLocation() {
		return location;
	}

	public int getId() {
		return id;
	}
	
	public MountainType getMountainType() {
		return type;
	}
}

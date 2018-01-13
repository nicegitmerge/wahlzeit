package org.wahlzeit.model;

import java.util.HashSet;

public class MountainType {
	
	private String type;
	
	protected HashSet<MountainType> subTypes = new HashSet<>();
	
	
	protected MountainType(String type) {
		this.type = type;
	}
	
	public Mountain createInstance() {
		return new Mountain(this);
	}
	
	public String getType() {
		return type;
	}
	
	
	public void addSubType(MountainType mt) {
		if (mt == null) {
			throw new IllegalArgumentException();
		}
		subTypes.add(mt);
	}
	
	public boolean isSubType(MountainType other) {
		return subTypes.contains(other);
	}
}

package org.wahlzeit.model;

import java.util.HashSet;

public class MountainType {
	
	private String type;
	
	protected HashSet<MountainType> subTypes = new HashSet<>();
	
	protected HashSet<Mountain> instances = new HashSet<>();
	
	
	protected MountainType(String type) {
		this.type = type;
	}
	
	public Mountain createInstance() {
		Mountain m = new Mountain(this);
		instances.add(m);
		return m;
	}
	
	public String getType() {
		return type;
	}
	
	
	public void addSubType(MountainType mt) {
		if (mt == null) {
			throw new IllegalArgumentException("argument null");
		}
		subTypes.add(mt);
	}
	
	public boolean isSubType(MountainType other) {
		return subTypes.contains(other);
	}
}

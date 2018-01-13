package org.wahlzeit.model;

import java.util.HashMap;

public class MountainManager {
	
	private static MountainManager instance;
	
	private HashMap<Integer, Mountain> mountains;
	private HashMap<String, MountainType> mountainTypes;
	
	
	private MountainManager() {
		mountains = new HashMap<>();
		mountainTypes = new HashMap<>();
	}
	
	public static MountainManager getInstance() {
		if (null == instance) {
			instance = new MountainManager();
		}
		return instance;
	}
	
	public Mountain createMountain(String type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		MountainType mt = getMountainType(type);
		Mountain result = mt.createInstance();
		mountains.put(result.getId(), result);
		return result;
	}
	
	public Mountain getMountain(int id) {
		return mountains.get(id);
	}

	private MountainType getMountainType(String type) {
		MountainType result = mountainTypes.get(type);
		if (result == null) {
			MountainType mt = new MountainType(type);
			mountainTypes.put(type, mt);
			result = mt;
		}
		return result;
	}
}

package org.wahlzeit.model;

import org.wahlzeit.model.Photo;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;
import com.googlecode.objectify.annotation.Serialize;

/**
 *
 */
@Subclass
@Entity
public class MountainPhoto extends Photo {
	
	@Serialize
	private Mountain mountain = null;

	public Mountain getMountain() {
		return mountain;
	}

	public void setMountain(Mountain mountain) {
		this.mountain = mountain;
	}

	/**
	 * @methodtype constructor
	 */
	public MountainPhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public MountainPhoto(PhotoId myId) {
		super(myId);
		if (null == myId) throw new IllegalArgumentException("PhotoId null");
	}
	
	/**
	 * @methodtype constructor
	 */
	public MountainPhoto(Mountain m) {
		super();
		if (m == null) {
			throw new IllegalArgumentException("argument null");
		}
		mountain = m;
	}
	
	/**
	 * @methodtype constructor
	 */
	public MountainPhoto(PhotoId myId, Mountain m) {
		super(myId);
		if (null == myId) throw new IllegalArgumentException("PhotoId null");
		if (m == null) {
			throw new IllegalArgumentException();
		}
	}

}

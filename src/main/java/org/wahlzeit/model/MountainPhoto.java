package org.wahlzeit.model;

import java.util.Date;

import org.wahlzeit.annotations.PatternInstance;
import org.wahlzeit.model.Photo;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;

/**
 *
 */
@Subclass
@Entity
public class MountainPhoto extends Photo {
	
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
			throw new IllegalArgumentException();
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

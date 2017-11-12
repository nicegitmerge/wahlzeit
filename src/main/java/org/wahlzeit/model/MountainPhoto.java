package org.wahlzeit.model;

import org.wahlzeit.model.Photo;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;

/**
 *
 */
@Subclass
@Entity
public class MountainPhoto extends Photo {

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
	}

}

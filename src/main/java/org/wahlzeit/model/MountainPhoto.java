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
	
	private int heightm; //meters

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
	
	/**
	 * @methodtype constructor
	 */
	public MountainPhoto(int heightm) {
		super();
		this.heightm = heightm;
	}
	
	/**
	 * @methodtype constructor
	 */
	public MountainPhoto(PhotoId myId, int heightm) {
		super(myId);
		this.heightm = heightm;
	}
	
	/**
	 * @methodtype get
	 */
	public int getHeightm() {
		return heightm;
	}
	
	/**
	 * @methodtype set
	 */
	public void setHeightm(int h) {
		heightm = h;
	}

}

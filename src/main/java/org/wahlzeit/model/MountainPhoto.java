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
	public MountainPhoto(PhotoId myId) throws IllegalArgumentCheckedException {
		super(myId);
		if (null == myId) throw new IllegalArgumentCheckedException("PhotoId null");
	}
	
	/**
	 * @methodtype constructor
	 * sets heightm to 0 if negative
	 */
	public MountainPhoto(int heightm) {
		super();
		this.heightm = heightm;
		if (heightm < 0) heightm = 0;
	}
	
	/**
	 * @methodtype constructor
	 * sets heightm to 0 if negative
	 */
	public MountainPhoto(PhotoId myId, int heightm) throws IllegalArgumentCheckedException {
		super(myId);
		if (null == myId) throw new IllegalArgumentCheckedException("PhotoId null");
		this.heightm = heightm;
		if (heightm < 0) heightm = 0;
	}
	
	/**
	 * @methodtype get
	 */
	public int getHeightm() {
		return heightm;
	}
	
	/**
	 * @methodtype set
	 * sets heightm to 0 if negative
	 */
	public void setHeightm(int h) {
		heightm = h;
		if (heightm < 0) heightm = 0;
	}

}

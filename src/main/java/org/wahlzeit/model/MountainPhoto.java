package org.wahlzeit.model;

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
		if (null == myId) throw new IllegalArgumentException("PhotoId null");
	}
	
	/**
	 * @methodtype constructor
	 */
	public MountainPhoto(int heightm) {
		super();
		if (heightm < 0) throw new IllegalArgumentException("heightm < 0");
		this.heightm = heightm;
	}
	
	/**
	 * @methodtype constructor
	 */
	public MountainPhoto(PhotoId myId, int heightm) {
		super(myId);
		if (null == myId) throw new IllegalArgumentException("PhotoId null");
		if (heightm < 0) throw new IllegalArgumentException("heightm < 0");
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
	 * sets heightm to 0 if negative
	 */
	public void setHeightm(int heightm) {
		if (heightm < 0) throw new IllegalArgumentException("heightm < 0");
		this.heightm = heightm;
	}

}

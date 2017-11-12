package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

public class MountainPhotoFactory extends PhotoFactory {
	
	private static final Logger log = Logger.getLogger(MountainPhotoFactory.class.getName());
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static PhotoFactory instance = null;

	protected MountainPhotoFactory() {
		// do nothing
	}
	
	/**
	 * Public singleton access method.
	 */
	
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic MountainPhotoFactory").toString());
			setInstance(new MountainPhotoFactory());
		}

		return instance;
	}
	
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(MountainPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize MountainPhotoFactory twice");
		}

		instance = photoFactory;
	}
	
	/**
	 * @methodtype factory
	 */
	public MountainPhoto createPhoto() {
		return new MountainPhoto();
	}
	
	/**
	 * Creates a new photo with the specified id
	 */
	public MountainPhoto createPhoto(PhotoId id) {
		return new MountainPhoto(id);
	}

}

package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * A photo manager provides access to and manages photos.
 */
public class MountainPhotoManager extends PhotoManager {

	/**
	 *
	 */
	private static final Logger log = Logger.getLogger(MountainPhotoManager.class.getName());

	/**
	 *
	 */
	public MountainPhotoManager() {
		photoTagCollector = MountainPhotoFactory.getInstance().createPhotoTagCollector();
	}

	/**
	 * @methodtype get
	 */
	public Photo getPhotoFromId(PhotoId id) {
		if (id == null) {
			return null;
		}

		Photo result = doGetPhotoFromId(id);

		if (result == null) {
			result = MountainPhotoFactory.getInstance().loadPhoto(id);
			if (result != null) {
				doAddPhoto(result);
			}
		}

		return result;
	}

	/**
	 * @methodtype command
	 *
	 * Load all persisted photos. Executed when Wahlzeit is restarted.
	 */
	public void loadPhotos() {
		Collection<MountainPhoto> existingPhotos = ObjectifyService.run(new Work<Collection<MountainPhoto>>() {
			@Override
			public Collection<MountainPhoto> run() {
				Collection<MountainPhoto> existingPhotos = new ArrayList<MountainPhoto>();
				readObjects(existingPhotos, MountainPhoto.class);
				return existingPhotos;
			}
		});

		for (Photo photo : existingPhotos) {
			if (!doHasPhoto(photo.getId())) {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Load MountainPhoto with ID", photo.getIdAsString()).toString());
				loadScaledImages(photo);
				doAddPhoto(photo);
			} else {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Already loaded MountainPhoto", photo.getIdAsString()).toString());
			}
		}

		log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());
	}

	@Override
	protected void updateDependents(Persistent obj) {
		if (obj instanceof MountainPhoto) {
			MountainPhoto photo = (MountainPhoto) obj;
			saveScaledImages(photo);
			updateTags(photo);
			UserManager userManager = UserManager.getInstance();
			Client owner = userManager.getClientById(photo.getOwnerId());
			userManager.saveClient(owner);
		}
	}

	/**
	 * query
	 */
	public Set<MountainPhoto> findMountainPhotosByOwner(String ownerName) {
		Set<MountainPhoto> result = new HashSet<MountainPhoto>();
		readObjects(result, MountainPhoto.class, MountainPhoto.OWNER_ID, ownerName);

		for (Iterator<MountainPhoto> i = result.iterator(); i.hasNext(); ) {
			doAddPhoto(i.next());
		}

		return result;
	}



	/**
	 * @methodtype factory
	 */
	public Photo createPhoto(String filename, Image uploadedImage) throws Exception {
		PhotoId id = PhotoId.getNextId();
		Photo result = PhotoUtil.createPhoto(filename, id, uploadedImage);
		addPhoto(result);
		return result;
	}

}

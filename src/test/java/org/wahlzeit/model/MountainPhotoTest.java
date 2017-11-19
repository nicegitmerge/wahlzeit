/**
 * 
 */
package org.wahlzeit.model;

import org.wahlzeit.model.MountainPhoto;
import org.wahlzeit.model.MountainPhotoFactory;
import org.wahlzeit.model.MountainPhotoManager;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 *
 */
public class MountainPhotoTest {
	
	private PhotoFactory factory;
	private PhotoManager manager;
	private Photo photo1;
	private Photo photo2;

	@ClassRule
	public static TestRule chain = RuleChain.
		outerRule(new LocalDatastoreServiceTestConfigProvider()).
		around(new RegisteredOfyEnvironmentProvider());
	
	@Before
	public void setUp() {
		factory = MountainPhotoFactory.getInstance();
		manager = MountainPhotoManager.getInstance();
		photo1 = new MountainPhoto();
		photo2 = new MountainPhoto(new PhotoId(5));
	}

	@Test
	public void testFactory() {
		assertNotEquals(null, factory);
		assertTrue(factory instanceof MountainPhotoFactory);
		PhotoId id = new PhotoId(1);
		Photo mp1 = factory.createPhoto();
		Photo mp2 = factory.createPhoto(id);
		Photo mp3 = ((MountainPhotoFactory)factory).createPhoto(8000);
		Photo mp4 = ((MountainPhotoFactory)factory).createPhoto(id, 8000);
		assertNotEquals(null, mp1);
		assertNotEquals(null, mp2);
		assertNotEquals(null, mp3);
		assertNotEquals(null, mp4);
		assertEquals(((MountainPhoto)mp3).getHeightm(), 8000);
		assertEquals(((MountainPhoto)mp4).getHeightm(), 8000);
		((MountainPhoto)mp3).setHeightm(3000);
		assertEquals(((MountainPhoto)mp3).getHeightm(), 3000);
	}
	
	@Test
	public void testManager() {
		assertNotNull(manager);
		assertTrue(manager instanceof MountainPhotoManager);
	}
	
	@Test
	public void testPhoto() {
		assertNotNull(photo1);
		assertNotNull(photo2);
		assertEquals(5, photo2.getId().value);
	}

}

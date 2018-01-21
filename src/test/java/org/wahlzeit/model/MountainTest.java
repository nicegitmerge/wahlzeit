package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MountainTest {
	
	MountainManager manager;
	
	Mountain tallMountain1;
	Mountain tallMountain2;
	Mountain smallMountain1;
	Mountain smallMountain2;
	
	
	@Before
	public void setUp() throws IllegalArgumentCheckedException {
		manager = MountainManager.getInstance();
	}

	@Test
	public void testMountain() throws IllegalArgumentCheckedException {
		tallMountain1 = manager.createMountain("Tall");
		tallMountain2 = manager.createMountain("Tall");
		smallMountain1 = manager.createMountain("Small");
		smallMountain2 = manager.createMountain("Small");
		
		assertNotNull(tallMountain1);
		assertNotNull(tallMountain2);
		assertNotNull(smallMountain1);
		assertNotNull(smallMountain2);
		
		assertEquals(manager.getMountain(0), tallMountain1);
		
		assertEquals(tallMountain1.getMountainType(), tallMountain2.getMountainType());
		assertEquals(smallMountain1.getMountainType(), smallMountain2.getMountainType());
		assertNotEquals(tallMountain1.getMountainType(), smallMountain2.getMountainType());
	}

}

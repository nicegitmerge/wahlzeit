package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {
	
	Location locOnes;
	Location locZeros;
	
	@Before
	public void setUp() throws IllegalArgumentCheckedException {
		locOnes = new Location(CartesianCoordinate.getCartesianCoordinate(1, 1, 1));
		locZeros = new Location(CartesianCoordinate.getCartesianCoordinate(0, 0, 0));
	}

	@Test
	public void testLocation() throws IllegalArgumentCheckedException {
		assertTrue(locOnes.equals(new Location(CartesianCoordinate.getCartesianCoordinate(1,1,1))));
		assertTrue(locZeros.equals(new Location(CartesianCoordinate.getCartesianCoordinate(0,0,0))));
		assertFalse(locOnes.equals(locZeros));
		assertFalse(locZeros.equals(null));
	}
	
	@Test(expected = IllegalArgumentCheckedException.class)
	public void testMethodsExpectIllegalArgumentException() throws IllegalArgumentCheckedException {
		Location l = new Location(null);
	}

}

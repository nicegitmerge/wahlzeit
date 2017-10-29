package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {
	
	Location locOnes;
	Location locZeros;
	
	@Before
	public void setUp() {
		locOnes = new Location(new Coordinate(1, 1, 1));
		locZeros = new Location(new Coordinate(0, 0, 0));
	}

	@Test
	public void testLocation() {
		assertTrue(locOnes.equals(new Location(new Coordinate(1,1,1))));
		assertTrue(locZeros.equals(new Location(new Coordinate(0,0,0))));
		assertFalse(locOnes.equals(locZeros));
		assertFalse(locZeros.equals(null));
	}

}

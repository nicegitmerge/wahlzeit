package org.wahlzeit.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {
	
	Coordinate zeros;
	Coordinate ones;
	
	@Before
	public void setUp() {
		zeros = new Coordinate(0, 0, 0);
		ones = new Coordinate(1, 1, 1);
	}
	
	@Test
	public void testGetDistance() {
		assertTrue(zeros.getDistance(zeros) == 0);
		assertTrue(zeros.getDistance(new Coordinate(0, 0, 0)) == 0);
		assertTrue(zeros.getDistance(new Coordinate(1, 0, 0)) == 1);
		assertTrue(zeros.getDistance(new Coordinate(0, 1, 0)) == 1);
		assertTrue(zeros.getDistance(new Coordinate(0, 0, 1)) == 1);
		assertTrue(zeros.getDistance(ones) == Math.sqrt(3));
		assertTrue(zeros.getDistance(new Coordinate(-1, -1, -1)) == Math.sqrt(3));
		assertTrue(ones.getDistance(new Coordinate(1, 1, 3)) == 2);
		assertTrue(zeros.getDistance(new Coordinate(0, 3, 4)) == 5);
	}

	@Test
	public void testIsEqual() {
		assertTrue(zeros.isEqual(zeros));
		assertTrue(zeros.isEqual(new Coordinate(0, 0, 0)));
		assertFalse(zeros.isEqual(new Coordinate(0, 0, 1e-3)));
		assertFalse(zeros.isEqual(ones));
		assertTrue(ones.isEqual(new Coordinate(1, 1, 1)));
		
		assertFalse(zeros.isEqual(null));
	}
	
	@Test
	public void testEquals() {
		assertTrue(zeros.equals(zeros));
		assertTrue(zeros.equals(new Coordinate(0, 0, 0)));
		assertFalse(zeros.equals(new Coordinate(0, 0, 1e-3)));
		assertFalse(zeros.equals(ones));
		assertFalse(zeros.equals(null));
		assertTrue(ones.equals(new Coordinate(1, 1, 1)));
		
		assertFalse(zeros.equals(null));
		assertFalse(zeros.equals(new Object()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMethodsExpectIllegalArgumentException() {
		zeros.getDistance(null);
	}

}

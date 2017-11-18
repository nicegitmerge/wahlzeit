package org.wahlzeit.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CartesianCoordinateTest {
	
	CartesianCoordinate zeros;
	CartesianCoordinate ones;
	
	@Before
	public void setUp() {
		zeros = new CartesianCoordinate(0, 0, 0);
		ones = new CartesianCoordinate(1, 1, 1);
	}
	
	@Test
	public void testGetDistance() {
		assertTrue(zeros.getDistance(zeros) == 0);
		assertTrue(zeros.getDistance(new CartesianCoordinate(0, 0, 0)) == 0);
		assertTrue(zeros.getDistance(new CartesianCoordinate(1, 0, 0)) == 1);
		assertTrue(zeros.getDistance(new CartesianCoordinate(0, 1, 0)) == 1);
		assertTrue(zeros.getDistance(new CartesianCoordinate(0, 0, 1)) == 1);
		assertTrue(zeros.getDistance(ones) == Math.sqrt(3));
		assertTrue(zeros.getDistance(new CartesianCoordinate(-1, -1, -1)) == Math.sqrt(3));
		assertTrue(ones.getDistance(new CartesianCoordinate(1, 1, 3)) == 2);
		assertTrue(zeros.getDistance(new CartesianCoordinate(0, 3, 4)) == 5);
	}

	@Test
	public void testIsEqual() {
		assertTrue(zeros.isEqual(zeros));
		assertTrue(zeros.isEqual(new CartesianCoordinate(0, 0, 0)));
		assertFalse(zeros.isEqual(new CartesianCoordinate(0, 0, 1e-3)));
		assertFalse(zeros.isEqual(ones));
		assertTrue(ones.isEqual(new CartesianCoordinate(1, 1, 1)));
		
		assertFalse(zeros.isEqual(null));
	}
	
	@Test
	public void testEquals() {
		assertTrue(zeros.equals(zeros));
		assertTrue(zeros.equals(new CartesianCoordinate(0, 0, 0)));
		assertFalse(zeros.equals(new CartesianCoordinate(0, 0, 1e-3)));
		assertFalse(zeros.equals(ones));
		assertFalse(zeros.equals(null));
		assertTrue(ones.equals(new CartesianCoordinate(1, 1, 1)));
		
		assertFalse(zeros.equals(null));
		assertFalse(zeros.equals(new Object()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMethodsExpectIllegalArgumentException() {
		zeros.getDistance(null);
	}
	
	@Test
	public void testAsSphericCoordinate() {
		double r = SphericCoordinate.EARTH_RADIUS;
		CartesianCoordinate a = new CartesianCoordinate(r, 0, 0);
		CartesianCoordinate b = new CartesianCoordinate(0, 0, r);
		CartesianCoordinate c = new CartesianCoordinate(0, 0, r);
		CartesianCoordinate d = new CartesianCoordinate(0, r, 0);
		CartesianCoordinate e = new CartesianCoordinate(0, -r, 0);
		CartesianCoordinate f = new CartesianCoordinate(-r, 0, 0);
		
		assertTrue(new SphericCoordinate(0, 0, r).isEqual(a.asSphericCoordinate()));
		assertTrue(new SphericCoordinate(0, 90, r).isEqual(b.asSphericCoordinate()));
		assertTrue(new SphericCoordinate(180, 90, r).isEqual(c.asSphericCoordinate()));
		assertTrue(new SphericCoordinate(90, 0, r).isEqual(d.asSphericCoordinate()));
		assertTrue(new SphericCoordinate(-90, 0, r).isEqual(e.asSphericCoordinate()));
		assertTrue(new SphericCoordinate(180, 0, r).isEqual(f.asSphericCoordinate()));
	}

}

package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {
	
	SphericCoordinate a, b, c, d, e, f;
	double r;

	@Before
	public void setUp() throws Exception {
		r = SphericCoordinate.EARTH_RADIUS;
		a = SphericCoordinate.getSphericCoordinate(0, 0, r);//equator, greenwich
		b = SphericCoordinate.getSphericCoordinate(0, 90, r);//north pole
		c = SphericCoordinate.getSphericCoordinate(180, 90, r);//north pole
		d = SphericCoordinate.getSphericCoordinate(90, 0, r);//equator
		e = SphericCoordinate.getSphericCoordinate(180, 0, r);//equator
		f = SphericCoordinate.getSphericCoordinate(123, -90, r);//south pole
	}
	
	@Test
	public void testIsEqual() {
		assertTrue(b.isEqual(c));
		assertTrue(e.isEqual(SphericCoordinate.getSphericCoordinate(-180, 0, r)));
	}

	@Test
	public void testAsCartesianCoordinate() {
		assertTrue(CartesianCoordinate.getCartesianCoordinate(r, 0, 0).isEqual(a.asCartesianCoordinate()));
		assertTrue(CartesianCoordinate.getCartesianCoordinate(0, 0, r).isEqual(b.asCartesianCoordinate()));
		assertTrue(CartesianCoordinate.getCartesianCoordinate(0, 0, r).isEqual(c.asCartesianCoordinate()));
		assertTrue(CartesianCoordinate.getCartesianCoordinate(0, r, 0).isEqual(d.asCartesianCoordinate()));
		assertTrue(CartesianCoordinate.getCartesianCoordinate(-r, 0, 0).isEqual(e.asCartesianCoordinate()));
	}
	
	@Test
	public void testGetSphericDistance() {
		assertEquals(a.getSphericDistance(d), a.getSphericDistance(b), 10.0d);
		assertEquals(a.getSphericDistance(a), 0.0d, 10.0d);
		assertEquals(a.getSphericDistance(e), a.getSphericDistance(d) * 2d, 10.0d);
		assertEquals(a.getSphericDistance(e), b.getSphericDistance(f), 10.0d);
	}
	
	@Test
	public void testGetCartesianDistance() {
		assertEquals(a.getCartesianDistance(CartesianCoordinate.getCartesianCoordinate(0, 0, 0)), r, 10.0d);
		assertEquals(b.getCartesianDistance(CartesianCoordinate.getCartesianCoordinate(0, 0, 0)), r, 10.0d);
		assertEquals(d.getCartesianDistance(e), Math.sqrt(2)*r, 10.0d);
	}
	
	@Test
	public void testSymmetricConversion() {
		SphericCoordinate s = SphericCoordinate.getSphericCoordinate(22, 43, r);
		SphericCoordinate t = SphericCoordinate.getSphericCoordinate(-180, -89, r);
		assertTrue(s.isEqual(s.asCartesianCoordinate().asSphericCoordinate()));
		assertTrue(t.isEqual(t.asCartesianCoordinate().asSphericCoordinate()));
	}
	
	@Test
	public void testTypes() {
		Coordinate s = SphericCoordinate.getSphericCoordinate(22, 43, r);
		Coordinate t = s.asCartesianCoordinate();
		assertTrue(s.isEqual(t));
		assertTrue(t.isEqual(s));
		assertTrue(s.getCartesianDistance(t) == t.getCartesianDistance(s));
		assertTrue(s.getSphericDistance(t) == t.getSphericDistance(s));
	}

}

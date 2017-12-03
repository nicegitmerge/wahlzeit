package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {
	
	private double x, y, z;
	
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants(); // preconditions are the same as the class invariants
	}
	
	public CartesianCoordinate(CartesianCoordinate other) {
		assertNotNull(other);
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
		assertClassInvariants();
	}
	
	protected double doGetCartesianDistance(CartesianCoordinate other) {
		double dx = other.x - x;
		double dy = other.y - y;
		double dz = other.z - z;
		assertClassInvariants();
		return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	protected double doGetSphericDistance(SphericCoordinate other) {
		return this.asSphericCoordinate().getSphericDistance(other);
	}
	
	protected boolean doIsEqual(Coordinate other) {
		CartesianCoordinate otherc = other.asCartesianCoordinate();
		assertClassInvariants();
		return (getCartesianDistance(otherc) < EPSILON);
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		assertClassInvariants();
		return new CartesianCoordinate(this);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		double r = Math.sqrt(x*x + y*y + z*z);
		double phi = (r < EPSILON ? Math.PI * 0.5d : Math.acos(z/r));
		double theta_p = (Math.abs(x) < EPSILON ? Math.PI * 0.5d : Math.atan(Math.abs(y/x)));
		double theta;
		if (x > 0 && y >= 0) {//Q1
			theta = theta_p;
		} else if (x <= 0 && y >= 0) {//Q2
			theta = Math.PI - theta_p;
		} else if (x < 0 && y < 0) {//Q3
			theta = Math.PI + theta_p;
		} else {
			theta = (2*Math.PI) - theta_p;
		}
		
		double lon = Math.toDegrees(theta);
		if (lon > 180) lon -= 360;
		if (lon < -180) lon += 360;
		double lat = -Math.toDegrees(phi) + 90;
		
		assertClassInvariants();
		return new SphericCoordinate(lon, lat, r);
	}
	
	/**
	 * @methodtype conversion
	 */
	public String toString() {
		return new String("x "+x+", y "+y+", z "+z);
	}
	
	/**
	 * @methodtype assert
	 */
	protected void assertClassInvariants() {
		assertDoubleVal(x);
		assertDoubleVal(y);
		assertDoubleVal(z);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}

}

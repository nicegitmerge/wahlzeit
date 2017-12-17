package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {
	
	private double x, y, z;
	
	private CartesianCoordinate(double x, double y, double z) {
		init(x, y, z);
		assertClassInvariants();
	}
	
	private CartesianCoordinate(CartesianCoordinate other) {
		other.assertClassInvariants();
		assertNotNull(other);
		init(other.x, other.y, other.z);
		assertClassInvariants();
	}
	
	public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		CartesianCoordinate c = new CartesianCoordinate(x,y,z);
		Coordinate ret = allCoordinates.get(c.hashCode());
		if (ret == null) {
			allCoordinates.put(c.hashCode(), c);
			return c;
		}
		return (CartesianCoordinate)ret;
	}
	
	private void init(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	protected double doGetCartesianDistance(CartesianCoordinate other) {
		other.assertClassInvariants();
		double dx = other.x - x;
		double dy = other.y - y;
		double dz = other.z - z;
		assertClassInvariants();
		other.assertClassInvariants();
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
		return SphericCoordinate.getSphericCoordinate(lon, lat, r);
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

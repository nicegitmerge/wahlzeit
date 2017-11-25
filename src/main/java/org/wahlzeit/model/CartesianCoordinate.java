package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {
	
	private double x, y, z;
	
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public CartesianCoordinate(CartesianCoordinate other) {
		assertNotNull(other);
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	@Override
	public double getCartesianDistance(Coordinate other) {
		assertNotNull(other);
		CartesianCoordinate coord = other.asCartesianCoordinate();
		double dx = coord.x - x;
		double dy = coord.y - y;
		double dz = coord.z - z;
		return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	@Override
	public double getSphericDistance(Coordinate other) {
		assertNotNull(other);
		return this.asSphericCoordinate().getSphericDistance(other.asSphericCoordinate());
	}
	
	protected boolean doIsEqual(Coordinate other) {
		CartesianCoordinate otherc = other.asCartesianCoordinate();
		return (getCartesianDistance(otherc) < EPSILON);
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
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
		
		return new SphericCoordinate(lon, lat, r);
	}
	
	@Override
	public String toString() {
		return new String("x "+x+", y "+y+", z "+z);
	}

}

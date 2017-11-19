package org.wahlzeit.model;

public class CartesianCoordinate implements Coordinate {
	
	public static final double EPSILON = 1e-6;
	
	private double x, y, z;
	
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public CartesianCoordinate(CartesianCoordinate other) {
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	@Override
	public double getCartesianDistance(Coordinate other) throws IllegalArgumentException {
		if (other == null) {
			throw new IllegalArgumentException("Parameter null");
		}
		CartesianCoordinate coord;
		if (other instanceof SphericCoordinate) {
			coord = ((SphericCoordinate)other).asCartesianCoordinate();
		} else {
			coord = (CartesianCoordinate)other;
		}
		double dx = coord.x - x;
		double dy = coord.y - y;
		double dz = coord.z - z;
		return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	@Override
	public double getSphericDistance(Coordinate other) {
		SphericCoordinate a, b;
		a = this.asSphericCoordinate();
		if (other instanceof CartesianCoordinate) {
			b = ((CartesianCoordinate)other).asSphericCoordinate();
		} else {
			b = (SphericCoordinate)other;
		}
		return a.getSphericDistance(b);
	}
	
	public double getDistance(Coordinate other) {
		return getCartesianDistance(other);
	}
	
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		
		CartesianCoordinate otherc;
		if (!(other instanceof CartesianCoordinate)) {
			otherc = other.asCartesianCoordinate();
		} else {
			otherc = (CartesianCoordinate) other;
		}
		
		return (getCartesianDistance(otherc) < EPSILON);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Coordinate)) return false;
		return isEqual((Coordinate)obj);
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
		
		SphericCoordinate ret = new SphericCoordinate(lon, lat, r);
		return ret;
	}
	
	@Override
	public String toString() {
		return new String(""+x+", "+y+", "+z);
	}

}

package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {
	
	public static final double EARTH_RADIUS = 6371;
	public static final double EPSILON = 1e-6;
	
	private double longitude, latitude, radius;
	
	public SphericCoordinate(double longitude, double latitude, double radius) throws IllegalArgumentException {
		if (Math.abs(latitude) > 90.0d) throw new IllegalArgumentException("latitude not in range -90, 90");
		if (longitude > 180.0d) {
			longitude -= 180;
		}
		if (longitude < -180.0d) {
			longitude += 180;
		}

		if (radius < 0) throw new IllegalArgumentException("radius < 0");
		this.longitude = longitude;
		this.latitude = latitude;
		this.radius = radius;
	}
	
	public SphericCoordinate(SphericCoordinate other) {
		this.longitude = other.longitude;
		this.latitude = other.latitude;
		this.radius = other.radius;
	}

	@Override
	public double getCartesianDistance(Coordinate other) {
		return this.asCartesianCoordinate().getCartesianDistance(other.asCartesianCoordinate());
	}

	@Override
	public double getSphericDistance(Coordinate other) {
		SphericCoordinate coord = other.asSphericCoordinate();
		double phi1 = Math.toRadians(latitude);
		double theta1 = Math.toRadians(longitude);
		double phi2 = Math.toRadians(coord.latitude);
		double theta2 = Math.toRadians(coord.longitude);
		double dphi = Math.abs(phi1 - phi2);
		double dtheta = Math.abs(theta1 - theta2);
		
		double dsigma = 2 * Math.asin(Math.sqrt(
				Math.sin(dphi*0.5)*Math.sin(dphi*0.5) + Math.cos(phi1)*Math.cos(phi2)*Math.sin(dtheta*0.5)*Math.sin(dtheta*0.5)
		));
		return radius * dsigma;
	}

	@Override
	public double getDistance(Coordinate other) {
		return getSphericDistance(other);
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		
		SphericCoordinate otherc = other.asSphericCoordinate();
		return (this.getSphericDistance(otherc) < EPSILON);
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
		double r = radius;
		double phi = Math.toRadians(-latitude + 90);
		double theta = Math.toRadians(longitude);
		
		double x = r*Math.sin(phi)*Math.cos(theta);
		double y = r*Math.sin(phi)*Math.sin(theta);
		double z = r*Math.cos(phi);
		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return new SphericCoordinate(this);
	}
	
	public String toString() {
		return new String(""+longitude+", "+latitude+", "+radius);
	}

}

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
		/*
		if (latitude > 90.0d) {
			latitude -= 90;
		}
		if (latitude < -90.0d) {
			latitude += 90;
		}
		*/
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
		CartesianCoordinate a, b;
		a = this.asCartesianCoordinate();
		if (other instanceof SphericCoordinate) {
			b = ((SphericCoordinate)other).asCartesianCoordinate();
		} else {
			b = (CartesianCoordinate)other;
		}
		return a.getCartesianDistance(b);
	}

	@Override
	public double getSphericDistance(Coordinate other) {
		SphericCoordinate coord;
		if (other instanceof SphericCoordinate) {
			coord = (SphericCoordinate)other;
		} else {
			coord = ((CartesianCoordinate)other).asSphericCoordinate();
		}
		double phi1 = Math.toRadians(latitude);//(-latitude + 90);
		double theta1 = Math.toRadians(longitude);//(longitude + 180);
		double phi2 = Math.toRadians(coord.latitude);//(-coord.latitude + 90);
		double theta2 = Math.toRadians(coord.longitude);//(coord.longitude + 180);
		double dphi = Math.abs(phi1 - phi2);
		double dtheta = Math.abs(theta1 - theta2);
		
		//double dsigma = Math.acos(Math.sin(phi1)*Math.sin(phi2) + Math.cos(phi1)*Math.cos(phi2)*Math.cos(dtheta));
		double dsigma = 2 * Math.asin(Math.sqrt(
				Math.sin(dphi*0.5)*Math.sin(dphi*0.5) + Math.cos(phi1)*Math.cos(phi2)*Math.sin(dtheta*0.5)*Math.sin(dtheta*0.5)
		));//better conditioned formula
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
		
		SphericCoordinate otherc;
		if (!(other instanceof SphericCoordinate)) {
			otherc = other.asSphericCoordinate();
		} else {
			otherc = (SphericCoordinate) other;
		}
		
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
		/*
		double r = radius;
		double phi = Math.toRadians(-latitude + 90);
		double theta = Math.toRadians(longitude + 180);
		
		double x = r*Math.sin(phi)*Math.cos(theta);
		double y = r*Math.sin(phi)*Math.sin(theta);
		double z = r*Math.cos(phi);
		return new CartesianCoordinate(x, y, z);
		*/
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
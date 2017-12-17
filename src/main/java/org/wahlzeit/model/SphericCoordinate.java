package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {
	
	public static final double EARTH_RADIUS = 6371;
	
	private double longitude, latitude, radius;
	
	private SphericCoordinate(double longitude, double latitude, double radius) {
		init(longitude, latitude, radius);
		assertClassInvariants();
	}
	
	private SphericCoordinate(SphericCoordinate other) {
		other.assertClassInvariants();
		assertNotNull(other);
		init(other.longitude, other.latitude, other.radius);
		assertClassInvariants();
	}
	
	public static SphericCoordinate getSphericCoordinate(double longitude, double latitude, double radius) {
		SphericCoordinate c = new SphericCoordinate(longitude, latitude, radius);
		Coordinate ret = allCoordinates.get(c.hashCode());
		if (ret == null) {
			allCoordinates.put(c.hashCode(), c);
			return c;
		}
		return (SphericCoordinate)ret;
	}
	
	private void init(double longitude, double latitude, double radius) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.radius = radius;
	}

	protected double doGetCartesianDistance(CartesianCoordinate other) {
		return this.asCartesianCoordinate().getCartesianDistance(other);
	}

	protected double doGetSphericDistance(SphericCoordinate other) {
		other.assertClassInvariants();
		assertEqualRadius(radius, other.radius);
		
		double phi1 = Math.toRadians(latitude);
		double theta1 = Math.toRadians(longitude);
		double phi2 = Math.toRadians(other.latitude);
		double theta2 = Math.toRadians(other.longitude);
		double dphi = Math.abs(phi1 - phi2);
		double dtheta = Math.abs(theta1 - theta2);
		
		double dsigma = 2 * Math.asin(Math.sqrt(
				Math.sin(dphi*0.5)*Math.sin(dphi*0.5) + Math.cos(phi1)*Math.cos(phi2)*Math.sin(dtheta*0.5)*Math.sin(dtheta*0.5)
		));
		assertClassInvariants();
		other.assertClassInvariants();
		return radius * dsigma;
	}

	protected boolean doIsEqual(Coordinate other) {		
		SphericCoordinate otherc = other.asSphericCoordinate();
		assertClassInvariants();
		return (this.getSphericDistance(otherc) < EPSILON);
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double r = radius;
		double phi = Math.toRadians(-latitude + 90);
		double theta = Math.toRadians(longitude);
		
		double x = r*Math.sin(phi)*Math.cos(theta);
		double y = r*Math.sin(phi)*Math.sin(theta);
		double z = r*Math.cos(phi);
		assertClassInvariants();
		return CartesianCoordinate.getCartesianCoordinate(x, y, z);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariants();
		return new SphericCoordinate(this);
	}
	
	/**
	 * @methodtype conversion
	 */
	public String toString() {
		return new String("longitude "+longitude+", latitude "+latitude+", radius "+radius);
	}
	
	/**
	 * @methodtype assert
	 */
	private void assertEqualRadius(double r, double s) {
		if (Math.abs(r - s) >= EPSILON) throw new IllegalArgumentException("coordinates of different radius");
	}
	
	/**
	 * @methodtype assert
	 */
	protected void assertClassInvariants() {
		assertDoubleVal(latitude);
		assertDoubleVal(longitude);
		assertDoubleVal(radius);
		if (Math.abs(latitude) > 90.0d) throw new IllegalArgumentException("latitude not in range -90, 90");
		if (Math.abs(longitude) > 180.0d) throw new IllegalArgumentException("longitude not in range -180, 180");
		if (radius < 0) throw new IllegalArgumentException("radius < 0");
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getRadius() {
		return radius;
	}

}

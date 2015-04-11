package com.sensorupchallenge.models;

public class Sensor {

	
	private String id;
	private double temp;
	private int heartRate;
	Location location;

	public Sensor() {
		this.id = "";
		this.temp = 0;
		this.heartRate = 0;
		this.location = new Location(0.0, 0.0);
	}

	public Sensor(String id, double temp, int heartRate, Location location) {
		this.id = id;
		this.temp = temp;
		this.heartRate = heartRate;
		this.location = location;
	}

	public int getHeartRate(){
		return this.heartRate;
	}
	
	public void setHeartRate(int heartRate){
		this.heartRate = heartRate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Sensor [id=" + id + ", temp=" + temp + ", location=" + location
				+ "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Sensor)){
			return false;
		}
		Sensor that = (Sensor) o;
		return this.id.equalsIgnoreCase(that.id);
	}
}

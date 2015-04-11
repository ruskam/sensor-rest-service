package com.sensorupchallenge.models;

import java.util.ArrayList;
import java.util.List;

public class ListTemp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String id, double temp, int heartRate, Location location
		List<Sensor> sensors = new ArrayList<Sensor>();
		Sensor s1 = new Sensor("id001", 68.4, 89, new Location(453.2342, 234.4342));
		Sensor s2 = new Sensor("id002", 6876, 67, new Location(453.2342, 234.4342));
		Sensor s3 = new Sensor("id003", 62, 354, new Location(453.2342, 234.4342));
		Sensor s4 = new Sensor("id004", 77.4, 849, new Location(453.2342, 234.4342));
		Sensor s5 = new Sensor("id005", 65.4, 839, new Location(453.2342, 234.4342));
		
//		System.out.println(sensors.get(""));
	}
	
	

}

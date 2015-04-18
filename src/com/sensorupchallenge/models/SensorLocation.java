package com.sensorupchallenge.models;

import java.util.Random;
import java.util.concurrent.Callable;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.UTMRef;


public class SensorLocation implements Callable<Location>{

	Random random;
	private final double RADIUS = 70;

	private final double CICLE_Center_LONG = -114.05191;
	private final double CIRCLE_CENTER_LAT = 51.03743;
	private final int CICLE_Center_Easting = 706681;
	private final int CICLE_Center_Northing = 5658123;

	private final int MAX_LONG = 706751;
	private final int MIN_LONG = 706611;

	private final int MAX_LAT = 5658193;
	private final int MIN_LAT = 5658053;

	private final char zoneNam = 'U';
	private final int zoneNum = 11;

	// private final double RADIUS_LAT = 0.00060;

	private int tempN = 0;
	private int tempE = 0;

	private int wrongN = 0;
	private int wrongE = 0;

	private int previousN = 0;
	private int previousE = 0;

	Location location;
	
	public SensorLocation() {
	};

	public Location call() throws Exception {

		random = new Random();
		int x = 0;
		while (true) {
			x++;
			tempE = random.nextInt((MAX_LONG - MIN_LONG) + 1) + MIN_LONG;
			tempN = random.nextInt((MAX_LAT - MIN_LAT) + 1) + MIN_LAT;
			/**
			if (previousN == 0 && previousE == 0) {
				tempE = random.nextInt((MAX_LONG - MIN_LONG) + 1) + MIN_LONG;
				tempN = random.nextInt((MAX_LAT - MIN_LAT) + 1) + MIN_LAT;
				System.out.println("no previous");
			} else {
				tempE = random.nextInt(((previousE + 3) - previousE) + 1)
						+ previousE;
				tempN = random.nextInt(((previousN + 3) - previousN) + 1)
						+ previousN;
				System.out.println("there are previous");
			}
			 */
			previousE = tempE;
			previousN = tempN;
			if (Math.pow(tempE - CICLE_Center_Easting, 2)
					+ Math.pow(tempN - CICLE_Center_Northing, 2) < Math.pow(
					RADIUS, 2)) {

				System.out.println("found");
				break;
			} else {
				UTMRef tempUTM = new UTMRef(tempE, tempN, zoneNam, zoneNum);
				System.out.println("wrong lat:" + tempUTM.toLatLng().getLat());
				System.out.println("wrong lng:" + tempUTM.toLatLng().getLng());
			}

		}
		UTMRef tempUTM = new UTMRef(tempE, tempN, zoneNam, zoneNum);
		double latTemp = tempUTM.toLatLng().getLat();
		double lngTemp = tempUTM.toLatLng().getLng();
		location = new Location(round(lngTemp, 6), round(latTemp,6));

		return location;
	}
	
	private double round(double value, int places) {
	    if (places < 0) {
	    	throw new IllegalArgumentException();
	    }

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}

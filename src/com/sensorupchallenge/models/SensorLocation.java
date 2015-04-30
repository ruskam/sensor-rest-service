package com.sensorupchallenge.models;

import java.util.Random;
import java.util.concurrent.Callable;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.UTMRef;


public class SensorLocation implements Callable<Location>{

	Random random;
	private final double RADIUS = 70;

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

	private int prevN, prevE;
	
	private static double previousLat = 0;
	private static double previousLng = 0;

	Location location, location1;
	
	public SensorLocation() {
	};

	public Location call() throws Exception {

		random = new Random();
		
		while (true) {
			
			//tempE = random.nextInt((MAX_LONG - MIN_LONG) + 1) + MIN_LONG;
			//tempN = random.nextInt((MAX_LAT - MIN_LAT) + 1) + MIN_LAT;
			
			if (previousLat == 0 && previousLng == 0) {
				System.out.println("no previous");
				tempE = random.nextInt((MAX_LONG - MIN_LONG) + 1) + MIN_LONG;
				tempN = random.nextInt((MAX_LAT - MIN_LAT) + 1) + MIN_LAT;
				
			} else {
				System.out.println("there is previous location");
				LatLng prevLatLng = new LatLng(previousLat, previousLng);
				prevN = (int)prevLatLng.toUTMRef().getNorthing();
				prevE = (int)prevLatLng.toUTMRef().getEasting();
				
				tempE = random.nextInt(((prevE + 3) - (prevE - 3)) + 1)
						+ (prevE - 3);
				
				tempN = random.nextInt(((prevN + 3) - (prevN-3)) + 1)
						+ (prevN - 3);
			}
			
			
			if (Math.pow(tempE - CICLE_Center_Easting, 2)
					+ Math.pow(tempN - CICLE_Center_Northing, 2) < Math.pow(
					RADIUS, 2)) {
				
				
				
				// distance check begin
				UTMRef prevUTM = new UTMRef(previousLng, previousLat, zoneNam, zoneNum);
				double latPrev = prevUTM.toLatLng().getLat();
				double lngPrev = prevUTM.toLatLng().getLng();
				System.out.println("prev:" + latPrev + ", " + lngPrev);
				
				UTMRef currUTM = new UTMRef(tempE, tempN, zoneNam, zoneNum);
				double latCurr = currUTM.toLatLng().getLat();
				double lngCurr = currUTM.toLatLng().getLng();
				System.out.println("curr" + latCurr + ", " + lngCurr);
				
				LatLng latLngPrev = new LatLng(latPrev, lngPrev);
				LatLng latLngCurr = new LatLng(latCurr, lngCurr);
				
				System.out.println("distance between previous and current: " + latLngCurr.distance(latLngPrev) * 1000);
				System.out.println("distance between previous and current FORMULA: " 
						+ Math.sqrt(Math.pow((prevN - tempN), 2) + Math.pow((prevE - tempE), 2)));
				//distance check end
				
				
				//previousE = tempE;
				//previousN = tempN;
				
				System.out.println("found");
				break;
			} else {
				//UTMRef tempUTM = new UTMRef(tempE, tempN, zoneNam, zoneNum);
				System.out.println("generating new coordinates...");
				//System.out.println("wrong lat:" + tempUTM.toLatLng().getLat());
				//System.out.println("wrong lng:" + tempUTM.toLatLng().getLng());
				
			}

		}
		/*
		UTMRef prevUTM = new UTMRef(previousE, previousN, zoneNam, zoneNum);
		double latPrev = prevUTM.toLatLng().getLat();
		double lngPrev = prevUTM.toLatLng().getLng();
		*/
		
		UTMRef currUTM = new UTMRef(tempE, tempN, zoneNam, zoneNum);
		double latCurr = currUTM.toLatLng().getLat();
		double lngCurr = currUTM.toLatLng().getLng();
		location = new Location(round(lngCurr, 6), round(latCurr,6));
		
		System.out.println("returned location :" + round(lngCurr, 6) + ", " + round(latCurr,6));
		
		
		return location;
	}
	
	public void setPreviousLocation(double lat, double lng){
		previousLat = lat;
		previousLng = lng;
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

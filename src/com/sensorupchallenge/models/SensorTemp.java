package com.sensorupchallenge.models;

import java.util.Random;
import java.util.concurrent.Callable;

public class SensorTemp implements Callable<Double>{
	
	Random random;
	
	private static final double BODY_TEMP_MIN = 38.0;
	private static final double BODY_TEMP_MAX = 39.2;
	
	public SensorTemp() {
		
	}

	@Override
	public Double call() throws Exception {
		random = new Random();
		double temp = BODY_TEMP_MIN + (BODY_TEMP_MAX - BODY_TEMP_MIN) * random.nextDouble();
		return temp;
	}
}

package com.sensorupchallenge.models;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public class SensorHeart implements Callable<Integer>{
	
	Random random;
	
	private static final int HEART_RATE_MIN = 60;
	private static final int HEART_RATE_MAX = 140;
	
	public SensorHeart() {
		
	}

	public Integer call() throws Exception {
		random = new Random();
		// TODO Auto-generated method stub
		int heartRate = random.nextInt((HEART_RATE_MAX - HEART_RATE_MIN) + 1) + HEART_RATE_MIN;
		return heartRate;
	}
	
}

package com.sensorupchallenge.dao;

import com.sensorupchallenge.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * mocked DAO in case data coming from the database
 */
public class SensorDAO {
	
	List<Sensor> sensors = new ArrayList<Sensor>();;
	
	private SensorDAO(){
	}
	
	public SensorDAO (int n) {
		
		for (int i = 1; i <= n; i++) {
			String idTemp = "id";
			if (i < 10) {
				idTemp = idTemp + "00" + i;
			}
			else if ((i > 9) && (i< 100)) {
				idTemp = idTemp + "0" + i;
			}
			else {
				idTemp += i;
			}
			Sensor sensor = new Sensor();
			sensor.setId(idTemp);
			sensors.add(sensor);
		}

	}
	
	public List<Sensor> getSensors() {
		this.generateSensorValues();
		return this.sensors;
	}
	
	public Sensor getSensorById(String id){
		this.generateSensorValues();
		for (Sensor sensor : this.sensors){
			if (sensor.getId().equals(id)){ 
				return sensor;
			}
		}
		return null;
	}
	
	private void generateSensorValues(){
		ExecutorService service =  Executors.newSingleThreadExecutor();
		for (Sensor sensor : sensors) {
			
			SensorTemp sensorT = new SensorTemp();
			SensorHeart sensorHR = new SensorHeart();
			SensorLocation sensorLocation = new SensorLocation();
			
			Future<Double> futureTemp = service.submit(sensorT);
			Future<Integer> futureHR = service.submit(sensorHR);
			Future<Location> futureSL = service.submit(sensorLocation);
			
			Double tempValue = null;
			Integer hrValue = null;
			Location locationValues = null;
			try {
				tempValue = futureTemp.get();
				hrValue = futureHR.get();
				locationValues = futureSL.get();
				
				sensorLocation.setPreviousLocation(locationValues.getY(), locationValues.getX());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sensor.setLocation(locationValues);
			sensor.setTemp(round(tempValue, 1));
			sensor.setHeartRate(hrValue);
			
		}	
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

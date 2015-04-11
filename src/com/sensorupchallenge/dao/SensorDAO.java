package com.sensorupchallenge.dao;

import com.sensorupchallenge.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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
		
		/* timer starts
		
		TimerClass tc = new TimerClass();
		System.out.println(tc.getNumber());
		int delay = 1000;
		int period = 1000;
		
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask(){
			int count = 0;
			 
            public void run() 
            {
            	++count;
                System.out.println( "Action:" + count ); 
                generateSensorValues();
                         
                //if(count == 5) 
               //     System.exit(0);
            } 
                 
        }, delay, period); 
		
		 timer ends*/
		
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
		for (Sensor sensor : sensors) {
			ExecutorService service =  Executors.newSingleThreadExecutor();
			SensorTemp sensorT = new SensorTemp();
			SensorHeart sensorHR = new SensorHeart();
			Future<Double> futureTemp = service.submit(sensorT);
			Future<Integer> futureHR = service.submit(sensorHR);
			Double tempValue = null;
			Integer hrValue = null;
			try {
				tempValue = futureTemp.get();
				hrValue = futureHR.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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

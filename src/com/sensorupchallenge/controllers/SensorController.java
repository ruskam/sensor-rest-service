package com.sensorupchallenge.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sensorupchallenge.dao.SensorDAO;
import com.sensorupchallenge.models.Sensor;

@RestController
@RequestMapping("/service/sensor")
public class SensorController {
	
	//List <SensorTemp> sensorThreading = new ArrayList<SensorTemp>();
	
	SensorDAO sensors = new SensorDAO(100);
	
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Sensor> getAllSensors() {
		List<Sensor> sensorsLocal = sensors.getSensors(); 
		return sensorsLocal;
	}
	
	@RequestMapping(value="/{rfid}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Sensor getSensorByID(@PathVariable String rfid) {

		Sensor sensor = sensors.getSensorById(rfid);
		return sensor;
	}
	
}
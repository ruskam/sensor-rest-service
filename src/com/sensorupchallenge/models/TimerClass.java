package com.sensorupchallenge.models;

import java.util.Random;

public class TimerClass {

	private int number;
	
	public TimerClass(){
		this.genNum();
	}
	
	private void genNum() {
		Random random = new Random();
		this.number = random.nextInt((140 - 60) + 1) + 60;
	}
	
	public int getNumber() {
		return this.number;
	} 
}

package com.sensorupchallenge.models;

import java.util.Timer;
import java.util.TimerTask;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimerClass tc = new TimerClass();
		System.out.println(tc.getNumber());
		int delay = 100;
		int period = 100;
		
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask(){
			int count = 0;
			 
            public void run() 
            {
            	++count;
                System.out.println( "Action:" + count ); 
                       
                         
                //if(count == 5) 
               //     System.exit(0);
            } 
                 
        }, delay, period); 
	}

}

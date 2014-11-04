package com.railroad.core;

import java.util.ArrayList;
import java.util.List;

public class Train {
	private static final int MAX_ACCELERATION = 10; 
	private ArrayList<Car> cars = new ArrayList<Car>();
	private int speed=0;
	
	public Train(){
		
	}
	
	public List<Car> getCars(){
		return cars;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void accelerate(int acceleration){
		if(acceleration > MAX_ACCELERATION)acceleration = MAX_ACCELERATION;
		
		speed += acceleration;
	}
	
	public void decelerate(int acceleration){
		if(acceleration > MAX_ACCELERATION)acceleration = MAX_ACCELERATION;
		
		speed -= acceleration;
	}
	
	public void move(){
		for(Car car : cars){
			car.move();
		}
	}
}

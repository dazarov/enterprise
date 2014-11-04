package com.railroad.core;

import java.awt.Point;

public class Car {
	private Point firstLocation, secondLocation;
	@SuppressWarnings("unused")
	private RailSegment firstSegment, secondSegment;
	private Train train;
	
	public Car(Train train, Point firstLocation, Point secondLocation){
		this.train = train;
		this.firstLocation = firstLocation;
		this.secondLocation = secondLocation;
	}
	
	public Point getFirstLocation(){
		return firstLocation;
	}
	
	public Point getSecondLocation(){
		return secondLocation;
	}
	
	public Train getTrain(){
		return train;
	}
	
	public int getSpeed(){
		return train.getSpeed();
	}
	
	public void move(){
		
	}
}

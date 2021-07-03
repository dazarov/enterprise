package com.daniel.utils.circular;

public class Configuration {
	private int angle = 360;
	private int oppositAngle = 180;
	private int step = 1;
	private int baseX = 500;
	private int baseY = 350;
	private int rateX = 300;
	private int rateY = 300;
	
	synchronized public int getAngle() {
		return angle;
	}
	
	synchronized public void setAngle(int angle) {
		this.angle = angle;
	}
	
	synchronized public int getOppositAngle() {
		return oppositAngle;
	}
	
	synchronized public void setOppositAngle(int oppositAngle) {
		this.oppositAngle = oppositAngle;
	}
	
	synchronized public int getStep() {
		return step;
	}
	
	synchronized public void setStep(int step) {
		this.step = step;
	}
	
	synchronized public int getBaseX() {
		return baseX;
	}
	
	synchronized public void setBaseX(int baseX) {
		this.baseX = baseX;
	}
	
	synchronized public int getBaseY() {
		return baseY;
	}
	
	synchronized public void setBaseY(int baseY) {
		this.baseY = baseY;
	}
	
	synchronized public int getRateX() {
		return rateX;
	}
	
	synchronized public void setRateX(int rateX) {
		this.rateX = rateX;
	}
	
	synchronized public int getRateY() {
		return rateY;
	}
	
	synchronized public void setRateY(int rateY) {
		this.rateY = rateY;
	}
}

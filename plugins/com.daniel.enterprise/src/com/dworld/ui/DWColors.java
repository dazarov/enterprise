package com.dworld.ui;

public class DWColors {
	protected final static DWColor SAND_COLOR = new DWColor(1f, 1f, 0);
	protected final static DWColor GRASS_COLOR = new DWColor(0, 0.5f, 0);
	protected final static DWColor WATER_COLOR = new DWColor(0, 1f, 1f);
	
	public final static class DWColor{
		private final float red;
		private final float green;
		private final float blue;
		public DWColor(float red, float green, float blue){
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		public float getRed(){
			return red;
		}
		
		public float getGreen(){
			return green;
		}
		
		public float getBlue(){
			return blue;
		}
	}
}

package com.railroad.core;

import java.awt.Point;
import java.util.ArrayList;

public class RailNode {
	@SuppressWarnings("unused")
	private int type;
	private ArrayList<RailSegment> segments = new ArrayList<RailSegment>();
	private Point location;
	
	public RailSegment getNextSegment(RailSegment current){
		for(RailSegment segment : segments){
			if(segment != current)
				return segment;
		}
		return null;
	}
	
	public Point getLocation(){
		return location;
	}
}

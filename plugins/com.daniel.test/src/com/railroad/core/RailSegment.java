package com.railroad.core;

import java.awt.Point;

public class RailSegment {
	private RailNode firstNode, secondNode;
	
	public RailNode getFirstNode(){
		return firstNode;
	}
	
	public RailNode getSecondNode(){
		return secondNode;
	}
	
	public Point getFirstLocation(){
		return firstNode.getLocation();
	}
	
	public Point getSecondLocation(){
		return secondNode.getLocation();
	}
	
	public RailNode getNextNode(RailNode current){
		if(current == firstNode)
			return secondNode;
		else
			return firstNode;
	}
}

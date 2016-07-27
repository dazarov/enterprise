package com.daniel.algorithms.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MergeIntervals {
	public static void main(String[] args){
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1,3));
		intervals.add(new Interval(2,6));
		intervals.add(new Interval(8,10));
		intervals.add(new Interval(15,18));
		
		ArrayList<Interval> result = merge(intervals);
		
		for(Interval i : result){
			System.out.print(i+" ");
		}
		
		System.out.println("");
		
		intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1,6));
		intervals.add(new Interval(2,4));
		intervals.add(new Interval(8,10));
		intervals.add(new Interval(15,18));
		
		result = merge(intervals);
		
		for(Interval i : result){
			System.out.print(i+" ");
		}
	}
	
	public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(intervals.size() == 0)
            return intervals;
        if(intervals.size() == 1)
            return intervals;
        
        Collections.sort(intervals, new IntervalComparator());
        
        Interval current = intervals.get(0);
        int start = current.start;
        int end = current.end;
        
        ArrayList<Interval> result = new ArrayList<Interval>();
        
        for(int i = 1; i < intervals.size(); i++){
            current = intervals.get(i);
            if(current.start <= end){
                end = Math.max(current.end, end);
            }else{
                result.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }
            
        }
        
        result.add(new Interval(start, end));
        
        return result;
        
    }
}

class Interval{
	int start;
	int end;
	
	Interval(){
		start = 0;
		end = 0;
	}
	
	Interval(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	public String toString(){
		return "["+start+", "+end+"]";
	}
}

class IntervalComparator implements Comparator<Interval>{
    public int compare(Interval i1, Interval i2){
        return i1.start - i2.start;
    }
}

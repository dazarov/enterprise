package com.dworld.core;

import java.awt.Frame;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import com.dworld.DWorldLauncher;
import com.dworld.ui.Map;

public class Engine extends Thread{
	private static final long MAIN_DELAY = 60;
	private static final long SLEEP_DELAY = 100;
	private static final int minimapRefreshRate = 10;
	
	private static Engine instance;
	
	public static Engine getEngine(){
		return instance;
	}
	
	private ArrayList<ISlow> slowUnits = new ArrayList<ISlow>();
	private ArrayList<IActive> activeUnits = new ArrayList<IActive>();
	private ArrayList<IActive> toDelete = new ArrayList<IActive>();
	
	private HashMap<Point, IUnit> allUnits = new HashMap<Point, IUnit>();
	
	private boolean run = true;
	private int maxElements = 0;
	private long time=0;
	private long maxTime=0;
	
	private boolean save = false;
	private boolean exit = false;
	private boolean load = false;
	
	private static long current=0;
	private static long delay=0;
	
	public Engine(Frame window){
		super();
		Engine.window = window;
		instance = this;
	}
	
	public void run(){
		
		while (true) {
			if(save){
				//createHash(window);
				Land.save(fileName, window);
				current = 0;
				time = 0;
				save = false;
				if(exit)
					System.exit(0);
			}
			if(load){
				clear();
				Land.load(fileName, window);
				init();
				current = 0;
				time = 0;
				load = false;
			}
			if(run){
				long t = System.currentTimeMillis();
				if(current != 0){
					time = t - current;
					if(time > maxTime)
						maxTime = time;
				}
				current = t;
				
				delay = MAIN_DELAY - time;
				
				if(delay > 0){
					try {
						sleep(delay);
					} catch (Exception ex) {
						ex.printStackTrace();
						return;
					}
				}
				loop();
			}else{
				current = 0;
				time = 0;
				try {
					sleep(SLEEP_DELAY);
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}
			}
			refreshMinimap();
			window.repaint();
		}
	}
	
	private static String fileName;
	private static Frame window;
	
	public void save(String fileName){
		Engine.fileName = fileName;
		save = true;
	}
	
	public void saveAndExit(String fileName){
		Engine.fileName = fileName;
		save = true;
		exit = true;
	}

	public void load(String fileName){
		Engine.fileName = fileName;
		load = true;
	}
	
	public int getNumberOfActiveUnits(){
		return activeUnits.size();
	}
	
	public int getNumberOfSlowUnits(){
		return slowUnits.size();
	}
	
	public int getMaxNumber(){
		return maxElements;
	}
	
	public long getCurrentDelta(){
		return time;
	}
	
	public long getMaxDelta(){
		return maxTime;
	}
	
	public void pause(boolean pause){
		run = !pause;
	}

	public void changeManCode(int code){
		pause(true);
		DWorldLauncher.getControlledUnit().setCode(code);
		pause(false);
	}
	
	public void init(){
		for(IUnit unit : allUnits.values()){
			unit.init();
		}
	}
	
	private void clear(){
		for(int i = activeUnits.size()-1; i >= 0; i--){
			IActive element = activeUnits.get(i);
			activeUnits.remove(element);
		}
		for(int i = slowUnits.size()-1; i >= 0; i--){
			IActive element = slowUnits.get(i);
			slowUnits.remove(element);
		}
		allUnits.clear();
//		for(int i = allUnits.size()-1; i >= 0; i--){
//			Unit element = allUnits.get(i);
//			allUnits.remove(element);
//		}
	}
	
	private int refreshCounter = minimapRefreshRate;
	
	private int slowIndex = 0;
	
	private void slowLoop(){
		// main loop
		IActive element;
		for (int i = slowIndex; i < slowUnits.size(); i+= 10) {
			element = slowUnits.get(i);
			if (element.isAlive()) {
				if(element.isActive()) element.step();
			} else {
				toDelete.add(element);
			}
		}

		// delete died elements
		for (int i = 0; i < toDelete.size(); i++) {
			element = toDelete.get(i);
			slowUnits.remove(element);
			//allUnits.remove(element);
			allUnits.remove(element);
		}
		toDelete.clear();
		
		slowIndex++;
		if(slowIndex > 10) slowIndex = 0; 
	}

	private void loop() {

		// main loop
		IActive element;
		for (int i = 0; i < activeUnits.size(); i++) {
			element = activeUnits.get(i);
			if (element.isAlive()) {
				if(element.isActive()) element.step();
			} else {
				toDelete.add(element);
			}
		}

		// delete died elements
		for (int i = 0; i < toDelete.size(); i++) {
			element = toDelete.get(i);
			activeUnits.remove(element);
			//allUnits.remove(element);
			allUnits.remove(element);
		}
		toDelete.clear();
		
		slowLoop();
	}
	
	private void refreshMinimap(){
		refreshCounter--;
		
		if(refreshCounter <= 0){
			refreshCounter = minimapRefreshRate;
			Map.refreshMinimap();
		}
	}

	public void addUnit(IUnit unit) {
		allUnits.put(unit.getLocation(), unit);
		
		if(unit instanceof IActive){
			if(unit instanceof ISlow){
				slowUnits.add((ISlow)unit);
			}else{
				activeUnits.add((IActive)unit);
				if(activeUnits.size() > maxElements){
					maxElements = activeUnits.size();
				}
			}
		}
	}
	
	public void moveUnit(IUnit unit){
		// for use in map <location, unit> only
		allUnits.remove(unit);
		allUnits.put(unit.getLocation(), unit);
	}

	public void removeElement(IActive element) {
		toDelete.add(element);
	}
	
	public IActive getElement(int index){
		return activeUnits.get(index);
	}
	
	public IActive getSlowElement(int index){
		return slowUnits.get(index);
	}
	
	public IUnit findUnit(Point location){
		return allUnits.get(location);
	}
	
	@Deprecated
	public IUnit[] slowFindUnit(Point location){
		ArrayList<IUnit> list = new ArrayList<IUnit>();
		for(IUnit unit : allUnits.values()){
			if(unit.getLocation().equals(location)){
				list.add(unit);
			}
		}
	    return list.toArray(new IUnit[]{});
	}
}

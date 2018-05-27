package com.dworld.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.dworld.ui.IProgressMonitor;

public class DWEngine implements Runnable {
	private static final long MAIN_DELAY = 60;
	private static final long SLEEP_DELAY = 100;
	private static final long STOP_DELAY = 500;
	private static final int minimapRefreshRate = 10;
	
	private List<ISlow> slowUnits = new ArrayList<>();
	private List<IActive> activeUnits = new ArrayList<>();
	private List<IActive> toDelete = new ArrayList<>();
	
	private Map<Location, LinkedList<IUnit>> allUnits = new HashMap<>();
	
	private long frameID = 0;
	
	private AtomicBoolean run = new AtomicBoolean(true);
	private AtomicBoolean pause = new AtomicBoolean(false);
	private int maxElements = 0;
	private long time=0;
	private long maxTime=0;
	
	private static long current=0;
	private static long delay=0;

	
	DWEngine(){
		super();
	}

	@Override
	public void run(){
		
		while (true) {
			if(!run.get()){
				try {
					Thread.sleep(STOP_DELAY);
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}
				continue;
			}
			
			if(!pause.get()){
				
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
						Thread.sleep(delay);
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
					Thread.sleep(SLEEP_DELAY);
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}
			}
			refreshMinimap();
			DWConfiguration.getInstance().getUI().repaint();
			frameID++;
			if(frameID == Long.MAX_VALUE){
				frameID = 0;
			}
		}
	}
	
	
	public void save(String fileName, IProgressMonitor progressMonitor){
		stop();
		Land.save(fileName, progressMonitor);
		current = 0;
		time = 0;
		start();
	}
	
	public void saveAndExit(String fileName, IProgressMonitor progressMonitor){
		save(fileName, progressMonitor);
		System.exit(0);
	}

	public void load(String fileName, IProgressMonitor progressMonitor){
		stop();
		clear();
		Land.load(fileName, progressMonitor);
		init();
		current = 0;
		time = 0;
		start();
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
	
	public long getFrameID(){
		return frameID;
	}
	
	public void pause(boolean pause){
		this.pause.set(pause);
	}
	
	private void stop(){
		run.set(false);
	}
	
	private void start(){
		run.set(true);
	}

	public void changeManCode(Land land){
		pause(true);
		DWConfiguration.getInstance().getControlledUnit().setLand(land);
		pause(false);
	}
	
	public void init(){
		for(List<IUnit> list : allUnits.values()){
			for(IUnit unit : list){
				unit.init();
			}
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
			removeUnitFromSearch(element);
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
			removeUnitFromSearch(element);
		}
		toDelete.clear();
		
		slowLoop();
	}
	
	private void refreshMinimap(){
		refreshCounter--;
		
		if(refreshCounter <= 0){
			refreshCounter = minimapRefreshRate;
			DWConfiguration.getInstance().getUI().refreshMinimap();
		}
	}
	
	private void addUnitToSearch(IUnit unit){
		LinkedList<IUnit> list = allUnits.get(unit.getLocation());
		if(list != null){
			list.add(unit);
		}else{
			list = new LinkedList<>();
			list.add(unit);
			allUnits.put(unit.getLocation(), list);
		}
	}
	
	private void removeUnitFromSearch(IUnit unit){
		LinkedList<IUnit> list = allUnits.get(unit.getLocation());
		if(list != null){
			list.remove(unit);
			if(list.isEmpty()){
				allUnits.remove(list);
			}
		}
	}

	public void addUnit(IUnit unit) {
		addUnitToSearch(unit);
		
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
	
	public void moveUnit(IUnit unit, Location prev){
		// for use in map <location, unit> only

		removeUnitFromSearch(unit);

		addUnitToSearch(unit);
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
	
	public List<IUnit> findUnit(Location location){
		return allUnits.get(location);
	}
}

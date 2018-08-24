package com.dworld.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dworld.ui.IProgressMonitor;

public class DWEngine implements Runnable {
	private static final long MAIN_DELAY = 60;
	private static final long SLEEP_DELAY = 100;
	private static final long STOP_DELAY = 500;
	private static final int minimapRefreshRate = 10;
	
	private static final Object EMPTY_VALUE = new Object();
	
	private List<ISlow> slowUnits = new CopyOnWriteArrayList<>();
	private Map<IActive, Object> activeUnits = new ConcurrentHashMap<>();
	private List<IActive> toDelete = new LinkedList<>();
	
	private Map<Location, LinkedList<IUnit>> allUnits = new ConcurrentHashMap<>();
	
	private long frameID = 0;
	
	private volatile boolean run = true;
	private volatile boolean pause = false;
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
			if(!run){
				try {
					Thread.sleep(STOP_DELAY);
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}
				continue;
			}
			
			if(!pause){
				
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
				step();
				
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
		this.pause = pause;
	}
	
	private void stop(){
		run = false;
	}
	
	private void start(){
		run = true;
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
		activeUnits.clear();
		
		slowUnits.clear();
		
		allUnits.clear();
	}
	
	private int refreshCounter = minimapRefreshRate;
	
	private int slowIndex = 0;
	
	private void slowStep(){
		// main step
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
		for (IActive elem : toDelete) {
			slowUnits.remove(elem);
			removeUnitFromSearch(elem, elem.getLocation());
		}
		toDelete.clear();
		
		slowIndex++;
		if(slowIndex > 10) slowIndex = 0; 
	}

	private void step() {
		// main step
		for (IActive element : activeUnits.keySet()) {
			if (element.isAlive()) {
				if(element.isActive()) element.step();
			} else {
				toDelete.add(element);
			}
		}

		// delete died elements
		for (IActive element : toDelete) {
			activeUnits.remove(element);
			removeUnitFromSearch(element, element.getLocation());
		}
		toDelete.clear();
		
		slowStep();
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
		if(list == null){
			list = new LinkedList<>();
			allUnits.put(unit.getLocation(), list);
		}
		list.add(unit);
	}
	
	private void removeUnitFromSearch(IUnit unit, Location location){
		LinkedList<IUnit> list = allUnits.get(location);
		if(list != null){
			list.remove(unit);
			if(list.isEmpty()){
				allUnits.remove(location);
			}
		}
	}

	public void addUnit(IUnit unit) {
		addUnitToSearch(unit);
		
		if(unit instanceof IActive){
			if(unit instanceof ISlow){
				slowUnits.add((ISlow)unit);
			}else{
				activeUnits.put((IActive)unit, EMPTY_VALUE);
				if(activeUnits.size() > maxElements){
					maxElements = activeUnits.size();
				}
			}
		}
	}
	
	public void moveUnit(IUnit unit, Location prev){
		// for use in map <location, unit> only

		removeUnitFromSearch(unit, prev);

		addUnitToSearch(unit);
	}

	public void removeElement(IActive element) {
		toDelete.add(element);
	}
	
	public Map<IActive, Object> getActiveUnits(){
		return activeUnits;
	}
	
	public IActive getSlowElement(int index){
		return slowUnits.get(index);
	}
	
	public List<IUnit> findUnit(Location location){
		return allUnits.get(location);
	}
}

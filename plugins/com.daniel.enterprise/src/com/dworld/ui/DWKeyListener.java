package com.dworld.ui;

import com.dworld.core.DWConfiguration;
import com.dworld.core.SelectionManager;
import com.dworld.ui.swing.DWMap;

public class DWKeyListener {
	/**
	 * Modifier could be
	 * 
	 * 0 - no Ctrl or Shift pressed
	 * 1 - Shift pressed
	 * 2 - Ctrl pressed
	 * 3 - Ctrl and Shift pressed
	 * 
	 * @param keyCode
	 * @param keyModifiers
	 */
	public void doKeyPressed(int keyCode, int keyModifiers){
		if (keyCode == 0)
			return;
		else if (keyCode == 27) {
			if(DWConfiguration.getInstance().getUI().exitConfirmation())
				System.exit(0);
		}else if (keyCode == 77) { // m
			DWMap.showMap();
		}else if (keyCode == 78) { // n
			DWMap.switchMinimap();
		}
		// Alt
		if(keyModifiers == 8 && !DWConfiguration.getInstance().isBuildMode()){
			switch(keyCode){
			case 37: // Left
				SelectionManager.modifySelection(0, 0, -1, 0);
				return;

			case 38: // Up
				SelectionManager.modifySelection(0, 0, 0, -1);
				return;

			case 39: // Right
				SelectionManager.modifySelection(0, 0, 1, 0);
				return;

			case 40: // Down
				SelectionManager.modifySelection(0, 0, 0, 1);
				return;
			}
		}
		if(keyModifiers == 0 && DWConfiguration.getInstance().isBuildMode()){
			switch(keyCode){
			case 37: // Left
				SelectionManager.moveLeft();
				return;

			case 38: // Up
				SelectionManager.moveUp();
				return;

			case 39: // Right
				SelectionManager.moveRight();
				return;

			case 40: // Down
				SelectionManager.moveDown();
				return;

			case 127: // Del
				SelectionManager.delete();
				return;
			}
		}
		// Ctrl
		if(keyModifiers == 2){
			if(keyCode == 67){ // Ctrl+c
				SelectionManager.copy();
				return;
			}else if(keyCode == 86){ // Ctrl+v
				SelectionManager.paste();
				return;
			}
		}
		
		if (DWConfiguration.getInstance().getControlledUnit() != null)
			DWConfiguration.getInstance().getControlledUnit().control(keyCode, keyModifiers);
		 
	}

}

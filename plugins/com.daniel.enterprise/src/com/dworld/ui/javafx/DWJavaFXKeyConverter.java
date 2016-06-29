package com.dworld.ui.javafx;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class DWJavaFXKeyConverter {
	private static final KeyCodeCombination ShiftUp = new KeyCodeCombination(KeyCode.UP, KeyCombination.SHIFT_DOWN);
	
	public static class KeyInfo{
		public int code;
		public int modifiers;
		
		public KeyInfo(int code, int modifiers){
			this.code = code;
			this.modifiers = modifiers;
		}
	}
	
	public static KeyInfo convert(KeyEvent event){
		
		int code = 0;
		int modifiers = 0;
		if(event.getCode() == KeyCode.ESCAPE){
			code = 27;
		}else if(event.getCode() == KeyCode.UP){
			code = 38;
		}else if(event.getCode() == KeyCode.DOWN){
			code = 40;
		}else if(event.getCode() == KeyCode.RIGHT){
			code = 39;
		}else if(event.getCode() == KeyCode.LEFT){
			code = 37;
		}
		
		return new KeyInfo(code, modifiers);
	}
}

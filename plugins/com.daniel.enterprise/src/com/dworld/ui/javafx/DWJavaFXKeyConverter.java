package com.dworld.ui.javafx;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCombination.Modifier;
import javafx.scene.input.KeyEvent;

public class DWJavaFXKeyConverter {
	
	private static Map<KeyCode, Integer> keys = new HashMap<KeyCode, Integer>();
	static{
		keys.put(KeyCode.ESCAPE, 27);
		keys.put(KeyCode.UP, 38);
		keys.put(KeyCode.DOWN, 40);
		keys.put(KeyCode.LEFT, 37);
		keys.put(KeyCode.RIGHT, 39);
		keys.put(KeyCode.M, 77);
		keys.put(KeyCode.N, 78);
		keys.put(KeyCode.C, 67);
		keys.put(KeyCode.V, 86);
		keys.put(KeyCode.L, 76);
		keys.put(KeyCode.D, 68);
		keys.put(KeyCode.F, 70);
		keys.put(KeyCode.W, 87);
		keys.put(KeyCode.S, 83);
		keys.put(KeyCode.PLUS, 107);
		keys.put(KeyCode.MINUS, 109);
		keys.put(KeyCode.R, 82);
		keys.put(KeyCode.T, 84);
		keys.put(KeyCode.SPACE, 32);
		keys.put(KeyCode.DIGIT0, (int)'0');
		keys.put(KeyCode.DIGIT1, (int)'1');
		keys.put(KeyCode.DIGIT2, (int)'2');
		keys.put(KeyCode.DIGIT3, (int)'3');
		keys.put(KeyCode.DIGIT4, (int)'4');
		keys.put(KeyCode.DIGIT5, (int)'5');
		keys.put(KeyCode.DIGIT6, (int)'6');
		keys.put(KeyCode.DIGIT7, (int)'7');
		keys.put(KeyCode.DIGIT8, (int)'8');
		keys.put(KeyCode.DIGIT9, (int)'9');
	}
	
	private static Set<Combination> combinations = new HashSet<Combination>();
	static{
		combinations.add(new Combination(KeyCombination.CONTROL_DOWN, KeyCode.UP));
		combinations.add(new Combination(KeyCombination.CONTROL_DOWN, KeyCode.DOWN));
		combinations.add(new Combination(KeyCombination.CONTROL_DOWN, KeyCode.LEFT));
		combinations.add(new Combination(KeyCombination.CONTROL_DOWN, KeyCode.RIGHT));
		
		combinations.add(new Combination(KeyCombination.CONTROL_DOWN, KeyCode.C));
		combinations.add(new Combination(KeyCombination.CONTROL_DOWN, KeyCode.V));

		combinations.add(new Combination(KeyCombination.ALT_DOWN, KeyCode.UP));
		combinations.add(new Combination(KeyCombination.ALT_DOWN, KeyCode.DOWN));
		combinations.add(new Combination(KeyCombination.ALT_DOWN, KeyCode.LEFT));
		combinations.add(new Combination(KeyCombination.ALT_DOWN, KeyCode.RIGHT));
	}
	
	
	public static KeyInfo convert(KeyEvent event){
		Integer code = 0;
		
		for(Combination comb : combinations){
			if(comb.codeCombination.match(event)){
				int modifiers = 0;
				if(comb.modifier == KeyCombination.CONTROL_DOWN){
					modifiers = 2;
				}else if(comb.modifier == KeyCombination.ALT_DOWN){
					modifiers = 8;
				}
				
				code = keys.get(comb.keyCode);
				
				return new KeyInfo(code, modifiers);
			}
		}
		
		if(event.getCode() != null){
			code = keys.get(event.getCode());
			if(code != null){
				return new KeyInfo(code, 0);
			}
		}
		
		return null;
	}
	
	public static class KeyInfo{
		public int code;
		public int modifiers;
		
		public KeyInfo(int code, int modifiers){
			this.code = code;
			this.modifiers = modifiers;
		}
	}
	
	private static class Combination{
		Modifier modifier;
		KeyCode keyCode;
		KeyCodeCombination codeCombination;
		
		Combination(Modifier modifier, KeyCode keyCode){
			this.modifier = modifier;
			this.keyCode = keyCode;
			codeCombination = new KeyCodeCombination(keyCode, modifier);
		}
	}
}

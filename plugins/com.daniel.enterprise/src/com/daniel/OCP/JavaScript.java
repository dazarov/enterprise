package com.daniel.OCP;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScript {
	public static void main(String[] args){
		
	}
	
	private void runJs(String js) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");
		System.out.println(engine.eval(js));
	}
}

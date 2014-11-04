package com.daniel.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class DUtils {
	// example
	// compareLoadedBundles(CDICorePlugin.getDefault().getBundle().getBundleContext(), "/home/daniel");
	
	
	public void compareLoadedBundles(BundleContext context, String filePath) throws Exception{
		File file = new File(filePath+"/bundles.txt");
		File logFile = new File(filePath+"/bundles.log");
		FileWriter log = new FileWriter(logFile);
		
		Bundle[] bds = context.getBundles();
		
		System.out.println("Bundles currently loaded - "+bds.length);
		log.write("\n\nBundles currently loaded - "+bds.length+"\n");
		
		ArrayList<String> names = new ArrayList<String>();
		for(Bundle bundle : bds){
			names.add(bundle.getSymbolicName());
		}

		if(file.exists()){
			System.out.println("File exists, reading stored bundles...");
			log.write("File exists, reading stored bundles...\n");
			
			ArrayList<String> stored = new ArrayList<String>();
			FileReader fr = new FileReader(file);
			BufferedReader in = new BufferedReader(fr); 
			String line = null;
		    while ((line = in.readLine()) != null) {
		    	stored.add(line.trim());
		        //System.out.println("Bundle - ["+line.trim()+"]");
		        //log.write("Bundle - ["+line.trim()+"]\n");
		    }
		    System.out.println("Bundles stored - "+stored.size());
		    log.write("Bundles stored - "+stored.size()+"\n");
		    
		    System.out.println("Compare bundle lists...");
		    log.write("\n\nCompare bundle lists...\n");
		    
	    	for(String name : names){
	    		if(!findBundle(name, stored)){
	    			System.out.println("Bundle "+name+" not found in stored bundles!");
	    			log.write("Bundle "+name+" not found in stored bundles!\n");
	    		}
	    	}
	    	
	    	log.write("\n\n");
	    	
	    	for(String name : stored){
	    		if(!findBundle(name, names)){
	    			System.out.println("Bundle "+name+" not found in currently loaded bundles!");
	    			log.write("Bundle "+name+" not found in currently loaded bundles!\n");
	    		}
	    	}
	    	
		    in.close();
		    fr.close();
		}else{
			System.out.println("File does not exists, writing current bundles...");
			log.write("File does not exists, writing current bundles...\n");
			FileWriter out = new FileWriter(file);
			for(String name : names){
				//System.out.println("Bundle - ["+name+"]");
				//log.write("Bundle - ["+name+"]\n");
				
				out.write(name+"\n");
			}
			out.close();
		}
		System.out.println("Done");
		log.write("Done\n");
		log.close();
		
	}
	
	private boolean findBundle(String name, ArrayList<String> names){
		for(String s : names){
			if(s.equals(name))
				return true;
		}
		return false;
	}

}

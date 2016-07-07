package com.dworld.ui.swing;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import com.dworld.core.DWConfiguration;
import com.dworld.ui.IProgressMonitor;


public class DWProgressMonitor extends JDialog implements IProgressMonitor{
	public static final long serialVersionUID = 1;
	
	private JProgressBar progressBar;
	
	
	public DWProgressMonitor(String title){
		super(DWConfiguration.getInstance().getUI().getWindow());
		setResizable(false);
		setTitle(title);
		progressBar = new JProgressBar(0, 100);
		progressBar.setPreferredSize(new Dimension(400, 20));
		progressBar.setValue(0);
		progressBar.setStringPainted(false);
		progressBar.setBorderPainted(true);
		progressBar.setIndeterminate(false);
		add(progressBar);
		pack();
		setLocation(getOwner().getLocation().x+getOwner().getSize().width/2-getSize().width/2, getOwner().getLocation().y+getOwner().getSize().height/2-getSize().height/2);
		setVisible(true);
	}
	
	@Override
	public void progress(int value){
    	//System.out.println("progress in EDT - "+SwingUtilities.isEventDispatchThread()+" value - "+value);

		progressBar.setValue(value);
//    	if(SwingUtilities.isEventDispatchThread()){
//    		//SwingUtilities.invokeLater(() -> monitor.progress((Integer) event.getNewValue()));
//    		monitor.progress((Integer) event.getNewValue());
//        	//new Thread(() -> monitor.progress((Integer) event.getNewValue())).start();
//    	}else{
//    		try {
//    			SwingUtilities.invokeAndWait(() -> monitor.progress((Integer) event.getNewValue()));
//    		} catch (InvocationTargetException | InterruptedException e) {
//    			e.printStackTrace();
//    		}
//    	}
		
	}
	
	@Override
	public void close(){
		setVisible(false);
		//dispose();
	}
}

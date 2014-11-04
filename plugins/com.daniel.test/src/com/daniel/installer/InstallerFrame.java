package com.daniel.installer;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;

public class InstallerFrame extends JFrame implements IProgressListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IProgress progress = null;
	JProgressBar monitor = null;
	
	public InstallerFrame() {
	    super("Installer");

	    //m_panel.addMouseListener(this);
	    getContentPane().setLayout(new BorderLayout());
	    monitor = new JProgressBar(0, 100);
	    getContentPane().add(monitor, BorderLayout.CENTER);
	    monitor.setMinimum(0);
	    monitor.setStringPainted(true);
	    
	    //monitor = new ProgressMonitor(null, "AAAAAAAAAA", "BBBBBBBBBBB", 0,100);
	    //m_panel.add(monitor);
	    setSize(800, 400);
	  }

	public void removeProgress(IProgress progress) {
		this.progress.removeProgressListener(this);
		progress = null;
	}

	public void addProgress(IProgress progress) {
		if(progress != null){
			this.progress = progress;
			progress.addProgressListener(this);
		}
	}
	String o_jobName="";
	int o_total=0;

	@Override
	public synchronized void progress(final String jobName, final int total, final int current) {
		System.out.println("progress "+jobName+ " "+total+" "+current);
		
		monitor.setValue(current);
		
		if(!jobName.equals(o_jobName))
			monitor.setString(jobName);
		
		if(total != o_total)
			monitor.setMaximum(total);
		
		
		o_jobName = jobName;
		o_total = total;
	}

}

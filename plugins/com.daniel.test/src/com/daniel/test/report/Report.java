package com.daniel.test.report;

import org.eclipse.ui.internal.views.log.LogEntry;

public class Report {
	private LogEntry logEntry;

	public Report(LogEntry logEntry) {
		this.logEntry = logEntry;
	}
	
	public String getSessionData(){
		return logEntry.getSession().getSessionData();
	}
	
	public String getStack(){
		return logEntry.getStack();
	}

}

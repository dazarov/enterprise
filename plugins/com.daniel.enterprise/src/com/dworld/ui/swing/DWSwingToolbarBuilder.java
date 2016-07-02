package com.dworld.ui.swing;

import javax.swing.JToolBar;

public class DWSwingToolbarBuilder {
	public JToolBar buildToolBar(){
		
		
		JToolBar toolBar = new JToolBar("DWorld toolbar");
		toolBar.setFloatable(false);
		toolBar.setFocusable(false);
		
		return toolBar;
	}
}

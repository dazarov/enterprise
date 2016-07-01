package com.dworld.ui.swing;

import java.awt.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.dworld.core.DWConfiguration;
import com.dworld.ui.DWMenu;
import com.dworld.ui.DWMenu.CheckboxMenuItem;
import com.dworld.ui.DWMenu.Menu;
import com.dworld.ui.DWMenu.MenuItem;
import com.dworld.ui.DWMenu.RadioMenuItem;

public class DWSwingMenuBuilder {
	
	public DWSwingMenuBuilder(){
	}
	
	public JMenuBar buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		List<Menu> menus = new DWMenu().menus;
		
		for(Menu menu : menus){
			createMenu(menu, menuBar);
		}
		
		return menuBar;
	}
	
	private void createMenu(Menu menu, Container parent){
		JMenu jMenu = new JMenu(menu.label);
		
		for(MenuItem item : menu.items){
			if(item instanceof Menu){
				createMenu((Menu)item, jMenu);
			}else if(item instanceof RadioMenuItem){
				createRadioItem((RadioMenuItem)item, jMenu);
			}else if(item instanceof CheckboxMenuItem){
				createCheckboxItem((CheckboxMenuItem)item, jMenu);
			}else{
				createMenuItem(item, jMenu);
			}
		}
		parent.add(jMenu);
	}
	
	Map<Integer, ButtonGroup> buttonGroups = new HashMap<Integer, ButtonGroup>();
	
	private ButtonGroup getButtonGroup(int id){
		ButtonGroup group = buttonGroups.get(id);
		if(group == null){
			group = new ButtonGroup();
			buttonGroups.put(id, group);
		}
		return group;
	}
	
	private void createRadioItem(RadioMenuItem radio, Container parent){
		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem(radio.label);
		if(radio.imageCode != -1){
			rbMenuItem.setIcon(new ImageIcon(DWConfiguration.getInstance().getUI().getImages(DWSwingImages.class).getImage(radio.imageCode)));
			if (DWConfiguration.getInstance().getSelectedMenu() == radio.imageCode){
				rbMenuItem.setSelected(true);
			}
		}
		rbMenuItem.addActionListener(e -> radio.runner.run());
		
		getButtonGroup(radio.buttonGroupId).add(rbMenuItem);
		parent.add(rbMenuItem);
	}
	
	private void createCheckboxItem(CheckboxMenuItem checkbox, Container parent){
		JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(checkbox.label);
		if(checkbox.isSelected != null){
			menuItem.setSelected(checkbox.isSelected.getAsBoolean());
		}
		if(checkbox.runner != null){
			menuItem.addActionListener(e -> checkbox.runner.run());
		}
		parent.add(menuItem);
	}
	
	private void createMenuItem(MenuItem item, Container parent){
		if(item.runner == null && parent instanceof JMenu){
			((JMenu)parent).addSeparator();
		}else{
			JMenuItem menuItem = new JMenuItem(item.label);
			if(item.imageCode != -1){
				menuItem.setIcon(new ImageIcon(DWConfiguration.getInstance().getUI().getImages(DWSwingImages.class).getImage(item.imageCode)));
			}
			menuItem.addActionListener(e -> item.runner.run());
			parent.add(menuItem);
		}
	}
}

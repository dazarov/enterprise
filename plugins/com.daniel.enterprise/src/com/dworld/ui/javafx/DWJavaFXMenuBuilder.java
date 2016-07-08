package com.dworld.ui.javafx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dworld.core.DWConfiguration;
import com.dworld.ui.DWMenuStructure;
import com.dworld.ui.DWMenuStructure.DWCheckboxMenuItem;
import com.dworld.ui.DWMenuStructure.DWMenu;
import com.dworld.ui.DWMenuStructure.DWMenuItem;
import com.dworld.ui.DWMenuStructure.DWRadioMenuItem;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class DWJavaFXMenuBuilder {
	public MenuBar buildMenu() {
		MenuBar menuBar = new MenuBar();
		
		List<DWMenu> menus = new DWMenuStructure().menus;
		
		for(DWMenu menu : menus){
			createMenu(menu, menuBar);
		}
		
		return menuBar;
	}
	
	private void createMenu(DWMenu menu, Object parent){
		Menu jMenu = new Menu(menu.label);
		
		for(DWMenuItem item : menu.items){
			if(item instanceof DWMenu){
				createMenu((DWMenu)item, jMenu);
			}else if(item instanceof DWRadioMenuItem){
				createRadioItem((DWRadioMenuItem)item, jMenu);
			}else if(item instanceof DWCheckboxMenuItem){
				createCheckboxItem((DWCheckboxMenuItem)item, jMenu);
			}else{
				createMenuItem(item, jMenu);
			}
		}
		if(parent instanceof MenuBar){
			((MenuBar)parent).getMenus().add(jMenu);
		}else if(parent instanceof Menu){
			((Menu)parent).getItems().add(jMenu);
		}
	}
	
	Map<Integer, ToggleGroup> buttonGroups = new HashMap<Integer, ToggleGroup>();
	
	private ToggleGroup getButtonGroup(int id){
		ToggleGroup group = buttonGroups.get(id);
		if(group == null){
			group = new ToggleGroup();
			buttonGroups.put(id, group);
		}
		return group;
	}
	
	private void createRadioItem(DWRadioMenuItem radio, Menu parent){
		RadioMenuItem rbMenuItem = new RadioMenuItem(radio.label);
		if(radio.imageCode != -1){
			rbMenuItem.setGraphic(new ImageView(DWConfiguration.getInstance().getUI().getImages(DWJavaFXImages.class).getImage(radio.imageCode)));
			if (DWConfiguration.getInstance().getSelectedCode() == radio.imageCode){
				rbMenuItem.setSelected(true);
			}
		}
		rbMenuItem.setOnAction(e -> radio.runner.run());
		
		rbMenuItem.setToggleGroup(getButtonGroup(radio.buttonGroupId));
		parent.getItems().add(rbMenuItem);
	}
	
	private void createCheckboxItem(DWCheckboxMenuItem checkbox, Menu parent){
		CheckMenuItem menuItem = new CheckMenuItem(checkbox.label);
		if(checkbox.isSelected != null){
			menuItem.setSelected(checkbox.isSelected.getAsBoolean());
		}
		if(checkbox.runner != null){
			menuItem.setOnAction(e -> checkbox.runner.run());
		}
		parent.getItems().add(menuItem);
	}
	
	private void createMenuItem(DWMenuItem item, Menu parent){
		if(item.runner == null){
			parent.getItems().add(new SeparatorMenuItem());
		}else{
			MenuItem menuItem = new MenuItem(item.label);
			if(item.imageCode != -1){
				menuItem.setGraphic(new ImageView(DWConfiguration.getInstance().getUI().getImages(DWJavaFXImages.class).getImage(item.imageCode)));
			}
			menuItem.setOnAction(e -> item.runner.run());
			parent.getItems().add(menuItem);
		}
	}

}

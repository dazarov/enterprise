package com.dworld.ui.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.dworld.DWSwingLauncher;
import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.core.SelectionManager;
import com.dworld.ui.DWImages;

public class DWSwingImages extends DWImages<Image>{
	protected Image loadImage(String path) {
		URL url = DWSwingLauncher.class.getClassLoader().getResource(path);
		if(url != null){
			return new ImageIcon(url).getImage();
		}else{
			return new ImageIcon(path).getImage();
		}
	}
	
	private Land land, background;
	private Image image;
	
	public void draw(Graphics g) {
		if(DWConfiguration.getInstance().getControlledUnit() != null)
			calculateLocation(DWConfiguration.getInstance().getControlledUnit().getDrawPosition());
		
		for (int x = 0; x < DWConstants.UI_WIDTH; x++) {
			for (int y = 0; y < DWConstants.UI_HEIGHT; y++) {
				background = Land.getBackground(startX + x, startY + y);
				land = Land.getForeground(startX + x, startY + y);
				g.setColor(Color.BLACK);
				g.fillRect(x * DWConstants.UI_IMAGE_WIDTH, y * DWConstants.UI_IMAGE_HEIGHT, DWConstants.UI_IMAGE_WIDTH,	DWConstants.UI_IMAGE_HEIGHT);

				if(background == Land.Sand){
					g.setColor(DWSwingColors.SAND);
					g.fillRect(x * DWConstants.UI_IMAGE_WIDTH, y * DWConstants.UI_IMAGE_HEIGHT, DWConstants.UI_IMAGE_WIDTH,	DWConstants.UI_IMAGE_HEIGHT);
				}else if(background == Land.Water){
					g.setColor(DWSwingColors.WATER);
					g.fillRect(x * DWConstants.UI_IMAGE_WIDTH, y * DWConstants.UI_IMAGE_HEIGHT, DWConstants.UI_IMAGE_WIDTH,	DWConstants.UI_IMAGE_HEIGHT);
				}else if(background == Land.Grass){
					g.setColor(DWSwingColors.GRASS);
					g.fillRect(x * DWConstants.UI_IMAGE_WIDTH, y * DWConstants.UI_IMAGE_HEIGHT, DWConstants.UI_IMAGE_WIDTH,	DWConstants.UI_IMAGE_HEIGHT);
				}else if(background != Land.Empty){
					g.drawImage(getImage(background), x * DWConstants.UI_IMAGE_WIDTH, y * DWConstants.UI_IMAGE_HEIGHT, DWConstants.UI_IMAGE_WIDTH,	DWConstants.UI_IMAGE_HEIGHT, null);
				}
				if(land != Land.Empty){
					image = getImage(land);
					g.drawImage(image, x * DWConstants.UI_IMAGE_WIDTH, y * DWConstants.UI_IMAGE_HEIGHT, DWConstants.UI_IMAGE_WIDTH,	DWConstants.UI_IMAGE_HEIGHT, null);
				}
			}
		}
		Rectangle area = SelectionManager.getSelectedArea();
		if(area != SelectionManager.NULL_RECTANGLE){
			g.setColor(Color.yellow);
			g.drawRect((area.x-startX)*DWConstants.UI_IMAGE_WIDTH, (area.y-startY)*DWConstants.UI_IMAGE_HEIGHT, area.width*DWConstants.UI_IMAGE_WIDTH-1, area.height*DWConstants.UI_IMAGE_HEIGHT-1);
		}
		ArrayList<Location> elements = SelectionManager.getSelectedLine();
		if(elements != null){
			g.setColor(Color.yellow);
			for(Location point : elements){
				g.drawRect((point.getX()-startX)*DWConstants.UI_IMAGE_WIDTH, (point.getY()-startY)*DWConstants.UI_IMAGE_HEIGHT, DWConstants.UI_IMAGE_WIDTH-1, DWConstants.UI_IMAGE_HEIGHT-1);
			}
		}
	}
}

package com.dworld.ui.javafx;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.Land;
import com.dworld.ui.DWImages;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DWJavaFXImages extends DWImages<Image>{

	@Override
	protected Image loadImage(String path) {
		return new Image("file:"+path, true);
	}
	private int code;
	private Image image;
	
	public void draw(GraphicsContext g) {
		if(DWConfiguration.getInstance().getControlledUnit() != null)
			calculateLocation(DWConfiguration.getInstance().getControlledUnit().getDrawPosition());
		
		for (int x = 0; x < DWConstants.UI_WIDTH; x++) {
			for (int y = 0; y < DWConstants.UI_HEIGHT; y++) {
				code = Land.getLand(startX + x, startY + y);
				if(code != Land.Empty){
					image = getImage(code);
					g.drawImage(image, (double) x * DWConstants.UI_IMAGE_WIDTH, (double)y * DWConstants.UI_IMAGE_HEIGHT, (double)DWConstants.UI_IMAGE_WIDTH, (double)	DWConstants.UI_IMAGE_HEIGHT);
				}else{
					g.fillRect((double) x * DWConstants.UI_IMAGE_WIDTH, (double)y * DWConstants.UI_IMAGE_HEIGHT, (double)DWConstants.UI_IMAGE_WIDTH, (double)	DWConstants.UI_IMAGE_HEIGHT);
				}
			}
		}
//		Rectangle area = SelectionManager.getSelectedArea();
//		if(area != SelectionManager.NULL_RECTANGLE){
//			g.setColor(Color.yellow);
//			g.drawRect((area.x-startX)*DWConstants.UI_IMAGE_WIDTH, (area.y-startY)*DWConstants.UI_IMAGE_HEIGHT, area.width*DWConstants.UI_IMAGE_WIDTH-1, area.height*DWConstants.UI_IMAGE_HEIGHT-1);
//		}
//		ArrayList<Location> elements = SelectionManager.getSelectedLine();
//		if(elements != null){
//			g.setColor(Color.yellow);
//			for(Location point : elements){
//				g.drawRect((point.getX()-startX)*DWConstants.UI_IMAGE_WIDTH, (point.getY()-startY)*DWConstants.UI_IMAGE_HEIGHT, DWConstants.UI_IMAGE_WIDTH-1, DWConstants.UI_IMAGE_HEIGHT-1);
//			}
//		}
	}

}

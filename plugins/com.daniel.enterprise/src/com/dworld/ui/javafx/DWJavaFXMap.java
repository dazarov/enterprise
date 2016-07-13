package com.dworld.ui.javafx;

import com.dworld.DWJavaFXLauncher.LongRunningTask;
import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.Land;
import com.dworld.ui.IProgressMonitor;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DWJavaFXMap {
	private static WritableImage image;
	
	public static void showMap(){
		image = null;
		image = new WritableImage(DWConstants.MAX_X, DWConstants.MAX_Y);
		LongRunningTask task = new LongRunningTask( m -> {
			PixelWriter writer = image.getPixelWriter();
	        drawImage(writer,0,0,DWConstants.MAX_X, DWConstants.MAX_Y, m);
		} );
		DWJavaFXProgressMonitor monitor = new DWJavaFXProgressMonitor("Map creating...");
		task.setOnSucceeded(e -> {monitor.close();doMap();});
		monitor.bind(task);
		new Thread(task).start();
	}
	
	public static void doMap(){
		Stage stage = new Stage();
		stage.initOwner(DWConfiguration.getInstance().getUI(DWJavaFXUI.class).getWindow());
        
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(new ImageView(image));
        
	    StackPane root = new StackPane();
	    root.getChildren().add(scroll);

	    Scene scene = new Scene(root, DWConstants.MAX_X, DWConstants.MAX_Y);

	    stage.setTitle("DWorld Map");
	    stage.setScene(scene);
	    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

	    stage.setX(primaryScreenBounds.getMinX());
	    stage.setY(primaryScreenBounds.getMinY());
	    stage.setWidth(primaryScreenBounds.getWidth());
	    stage.setHeight(primaryScreenBounds.getHeight());
	    stage.show();
	    
	}
	
	static void drawRegion(GraphicsContext g, int startX, int startY, int width, int height, IProgressMonitor monitor){
		int progress = 0;
		for(int x = startX, windowX = 0; x < (startX+width); x++, windowX++){
			if(monitor != null && progress != windowX*100/width){
				progress = windowX*100/width;
				monitor.progress(progress);	
			}
			for(int y = startY, windowY = 0; y < (startY+height); y++, windowY++){
				int code = Land.getLand(x, y);
				if(code != Land.Empty){
					switch(code){
					case Land.Brick:
					case Land.ClosedHorizontalBrickGate:
					case Land.OpenedHorizontalBrickGate:
					case Land.ClosedVerticalBrickGate:
					case Land.OpenedVerticalBrickGate:
					case Land.ClosedHorizontalWoodGate:
					case Land.OpenedHorizontalWoodGate:
					case Land.ClosedVerticalWoodGate:
					case Land.OpenedVerticalWoodGate:
						
						g.setStroke(Color.RED);
						break;
						
					case Land.Wall:
					case Land.ClosedHorizontalSteelGate:
					case Land.OpenedHorizontalSteelGate:
					case Land.ClosedVerticalSteelGate:
					case Land.OpenedVerticalSteelGate:
					case Land.ClosedHorizontalConcreteGate:
					case Land.OpenedHorizontalConcreteGate:
					case Land.ClosedVerticalConcreteGate:
					case Land.OpenedVerticalConcreteGate:
					case Land.Rail_Vertical:
					case Land.Rail_Horizontal:
					case Land.Rail_Diagonal_Up:
					case Land.Rail_Diagonal_Down:
					case Land.Rail_Up_Right:
					case Land.Rail_Up_Left:
					case Land.Rail_Down_Right:
					case Land.Rail_Down_Left:
					case Land.Rail_Right_Up:
					case Land.Rail_Right_Down:
					case Land.Rail_Left_Up:
					case Land.Rail_Left_Down:
					case Land.Rail_Vertical_Cross:
					case Land.Rail_Diagonal_Cross:
					case Land.Station_Horizontal:
					case Land.Station_Vertical:
						
						g.setStroke(Color.GRAY);
						break;
						
					case Land.Grass:
						g.setStroke(Color.GREEN);
						break;
						
					case Land.Water:
						g.setStroke(Color.BLUE);
						break;
						
					case Land.Sand:
						g.setStroke(Color.YELLOW);
						break;
					case Land.Mountain:
						g.setStroke(Color.MAGENTA);
						break;
					case Land.Wood1:
					case Land.Wood2:
					case Land.Wood3:
					case Land.Wood4:
						g.setStroke(Color.ORANGE);
						break;
						
					case Land.Tree1:
					case Land.Tree2:
					case Land.Tree3:
						g.setStroke(new Color(0,0.39,0,1));
						break;

					case Land.Ammo:
					case Land.Grenade:
					case Land.Rocket:
						g.setStroke(Color.PINK);
						break;
						
						default:
							g.setStroke(Color.WHITE);
					}
					g.strokeLine(windowX, windowY, windowX, windowY);
				}//else{
				//	g.setStroke(Color.BLACK);
				//	g.strokeLine(windowX, windowY, windowX, windowY);
				//}
			}
		}
	}
	
	static void drawImage(PixelWriter writer, int startX, int startY, int width, int height, IProgressMonitor monitor){
		int progress = 0;
		for(int x = startX, windowX = 0; x < (startX+width); x++, windowX++){
			if(monitor != null && progress != windowX*100/width){
				progress = windowX*100/width;
				monitor.progress(progress);	
			}
			for(int y = startY, windowY = 0; y < (startY+height); y++, windowY++){
				int code = Land.getLand(x, y);
				if(code != Land.Empty){
					switch(code){
					case Land.Brick:
					case Land.ClosedHorizontalBrickGate:
					case Land.OpenedHorizontalBrickGate:
					case Land.ClosedVerticalBrickGate:
					case Land.OpenedVerticalBrickGate:
					case Land.ClosedHorizontalWoodGate:
					case Land.OpenedHorizontalWoodGate:
					case Land.ClosedVerticalWoodGate:
					case Land.OpenedVerticalWoodGate:
						writer.setColor(windowX, windowY, Color.RED);
						break;
						
					case Land.Wall:
					case Land.ClosedHorizontalSteelGate:
					case Land.OpenedHorizontalSteelGate:
					case Land.ClosedVerticalSteelGate:
					case Land.OpenedVerticalSteelGate:
					case Land.ClosedHorizontalConcreteGate:
					case Land.OpenedHorizontalConcreteGate:
					case Land.ClosedVerticalConcreteGate:
					case Land.OpenedVerticalConcreteGate:
					case Land.Rail_Vertical:
					case Land.Rail_Horizontal:
					case Land.Rail_Diagonal_Up:
					case Land.Rail_Diagonal_Down:
					case Land.Rail_Up_Right:
					case Land.Rail_Up_Left:
					case Land.Rail_Down_Right:
					case Land.Rail_Down_Left:
					case Land.Rail_Right_Up:
					case Land.Rail_Right_Down:
					case Land.Rail_Left_Up:
					case Land.Rail_Left_Down:
					case Land.Rail_Vertical_Cross:
					case Land.Rail_Diagonal_Cross:
					case Land.Station_Horizontal:
					case Land.Station_Vertical:
						
						writer.setColor(windowX, windowY, Color.GRAY);
						break;
						
					case Land.Grass:
						writer.setColor(windowX, windowY, Color.GREEN);
						break;
						
					case Land.Water:
						writer.setColor(windowX, windowY, Color.BLUE);
						break;
						
					case Land.Sand:
						writer.setColor(windowX, windowY, Color.YELLOW);
						break;
						
					case Land.Mountain:
						writer.setColor(windowX, windowY, Color.MAGENTA);
						break;
						
					case Land.Wood1:
					case Land.Wood2:
					case Land.Wood3:
					case Land.Wood4:
						writer.setColor(windowX, windowY, Color.ORANGE);
						break;
						
					case Land.Tree1:
					case Land.Tree2:
					case Land.Tree3:
						writer.setColor(windowX, windowY, new Color(0,0.39,0,1));
						break;

					case Land.Ammo:
					case Land.Grenade:
					case Land.Rocket:
						writer.setColor(windowX, windowY, Color.PINK);
						break;
						
						default:
							writer.setColor(windowX, windowY, Color.WHITE);
					}
				}else{
				  writer.setColor(windowX, windowY, Color.BLACK);
				}
			}
		}
	}

}

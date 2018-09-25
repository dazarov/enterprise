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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
		task.setOnCancelled(e -> monitor.close());
		monitor.bind(task);
		new Thread(task, "Map Creator").start();
	}
	
	public static void doMap(){
		Stage stage = new Stage();
		stage.initOwner(DWConfiguration.getInstance().getUI(DWJavaFXUI.class).getWindow());
        
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(new ImageView(image));
        
	    StackPane root = new StackPane();
	    root.getChildren().add(scroll);

	    Scene scene = new Scene(root, DWConstants.MAX_X, DWConstants.MAX_Y);
	    
        scene.setOnKeyPressed(event -> {
			if (event.getEventType() == KeyEvent.KEY_PRESSED) {
				if(event.getCode().equals(KeyCode.ESCAPE)){
					stage.close();
				}
			}
        });

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
				Land land = Land.getLand(x, y);
				if(land != Land.Empty){
					switch(land){
					case Brick:
					case ClosedHorizontalBrickGate:
					case OpenedHorizontalBrickGate:
					case ClosedVerticalBrickGate:
					case OpenedVerticalBrickGate:
					case ClosedHorizontalWoodGate:
					case OpenedHorizontalWoodGate:
					case ClosedVerticalWoodGate:
					case OpenedVerticalWoodGate:
						
						g.setStroke(Color.RED);
						break;
						
					case Wall:
					case ClosedHorizontalSteelGate:
					case OpenedHorizontalSteelGate:
					case ClosedVerticalSteelGate:
					case OpenedVerticalSteelGate:
					case ClosedHorizontalConcreteGate:
					case OpenedHorizontalConcreteGate:
					case ClosedVerticalConcreteGate:
					case OpenedVerticalConcreteGate:
					case Rail_Vertical:
					case Rail_Horizontal:
					case Rail_Diagonal_Up:
					case Rail_Diagonal_Down:
					case Rail_Up_Right:
					case Rail_Up_Left:
					case Rail_Down_Right:
					case Rail_Down_Left:
					case Rail_Right_Up:
					case Rail_Right_Down:
					case Rail_Left_Up:
					case Rail_Left_Down:
					case Rail_Vertical_Cross:
					case Rail_Diagonal_Cross:
					case Station_Horizontal:
					case Station_Vertical:
						
						g.setStroke(Color.GRAY);
						break;
						
					case Grass:
						g.setStroke(Color.GREEN);
						break;
						
					case Water:
						g.setStroke(Color.BLUE);
						break;
						
					case Sand:
						g.setStroke(Color.YELLOW);
						break;
					case Mountain:
						g.setStroke(Color.MAGENTA);
						break;
					case Wood1:
					case Wood2:
					case Wood3:
					case Wood4:
						g.setStroke(Color.ORANGE);
						break;
						
					case Tree1:
					case Tree2:
					case Tree3:
						g.setStroke(new Color(0,0.39,0,1));
						break;

					case Ammo:
					case Grenade:
					case Rocket:
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
			if(monitor.isCancelled()){
				return;
			}
			if(progress != windowX*100/width){
				progress = windowX*100/width;
				monitor.progress(progress);	
			}
			for(int y = startY, windowY = 0; y < (startY+height); y++, windowY++){
				Land land = Land.getLand(x, y);
				if(land != Land.Empty){
					switch(land){
					case Brick:
					case ClosedHorizontalBrickGate:
					case OpenedHorizontalBrickGate:
					case ClosedVerticalBrickGate:
					case OpenedVerticalBrickGate:
					case ClosedHorizontalWoodGate:
					case OpenedHorizontalWoodGate:
					case ClosedVerticalWoodGate:
					case OpenedVerticalWoodGate:
						writer.setColor(windowX, windowY, Color.RED);
						break;
						
					case Wall:
					case ClosedHorizontalSteelGate:
					case OpenedHorizontalSteelGate:
					case ClosedVerticalSteelGate:
					case OpenedVerticalSteelGate:
					case ClosedHorizontalConcreteGate:
					case OpenedHorizontalConcreteGate:
					case ClosedVerticalConcreteGate:
					case OpenedVerticalConcreteGate:
					case Rail_Vertical:
					case Rail_Horizontal:
					case Rail_Diagonal_Up:
					case Rail_Diagonal_Down:
					case Rail_Up_Right:
					case Rail_Up_Left:
					case Rail_Down_Right:
					case Rail_Down_Left:
					case Rail_Right_Up:
					case Rail_Right_Down:
					case Rail_Left_Up:
					case Rail_Left_Down:
					case Rail_Vertical_Cross:
					case Rail_Diagonal_Cross:
					case Station_Horizontal:
					case Station_Vertical:
						
						writer.setColor(windowX, windowY, Color.GRAY);
						break;
						
					case Grass:
						writer.setColor(windowX, windowY, Color.GREEN);
						break;
						
					case Water:
						writer.setColor(windowX, windowY, Color.BLUE);
						break;
						
					case Sand:
						writer.setColor(windowX, windowY, Color.YELLOW);
						break;
						
					case Mountain:
						writer.setColor(windowX, windowY, Color.MAGENTA);
						break;
						
					case Wood1:
					case Wood2:
					case Wood3:
					case Wood4:
						writer.setColor(windowX, windowY, Color.ORANGE);
						break;
						
					case Tree1:
					case Tree2:
					case Tree3:
						writer.setColor(windowX, windowY, new Color(0,0.39,0,1));
						break;

					case Ammo:
					case Grenade:
					case Rocket:
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

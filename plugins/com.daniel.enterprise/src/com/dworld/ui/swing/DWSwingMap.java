package com.dworld.ui.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingWorker.StateValue;

import com.dworld.DWSwingLauncher.LongRunningTask;
import com.dworld.core.DWConfiguration;
import com.dworld.core.Land;
import com.dworld.core.Location;
import com.dworld.ui.IProgressMonitor;
import com.dworld.ui.NullProgressMonitor;

public class DWSwingMap {
	private static Image image;
	static DWSwingWindow minimap = null;
	static Location drawPoint = null;
	
	static JFrame window;
	
	public static void showMap(){
		//System.out.println("Show Map...");
		image = null;
		createImage();
	}
	
	private static void doMap(){
		//System.out.println("DO Map...");
		if(image == null || window != null){
			return;
		}
		window = new JFrame();
		window.setTitle("DWorld Map");
		window.setLayout(new GridLayout());
		
		JPanel panel = new JPanel(){
			static final long serialVersionUID = 15;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		panel.setBackground(Color.black);
		panel.setSize(Land.getMaxX(), Land.getMaxY());
		panel.setPreferredSize(panel.getSize());
		
		JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.getVerticalScrollBar().setUnitIncrement(30);
		scroll.getHorizontalScrollBar().setUnitIncrement(30);
		
		window.add(scroll);
		window.pack();
		scroll.getVerticalScrollBar().setValue(750);
		scroll.getHorizontalScrollBar().setValue(600);
		window.setLocation(0, 0);
		window.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		window.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				window = null;
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}
		});
		window.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == 27) {
					window.dispose();
					window = null;
				}
			}
			public void keyReleased(KeyEvent event) {
			}
			public void keyTyped(KeyEvent event) {
			}
		});
		window.setVisible(true);
	}
	
	
	
	public static void refreshMinimap(){
		if(minimap != null){
			if(drawPoint == null)
				drawPoint = DWConfiguration.getInstance().getControlledUnit().getDrawPosition();
			else{
				if(!DWConfiguration.getInstance().getControlledUnit().getDrawPosition().equals(drawPoint)){
					minimap.repaint();
					drawPoint = DWConfiguration.getInstance().getControlledUnit().getDrawPosition();
				}
			}
			
		}
	}
	
	public static void toggleMinimap(){
		if(minimap == null)
			showMinimap();
		else
			closeMinimap();
	}
	
	public static void closeMinimap(){
		if(minimap != null){
			DWSwingWindowListener.getDefault().removeWindow(minimap);
			minimap.close();
			minimap = null;
		}
	}
	
	public static void showMinimap(){
		if(minimap != null){
			minimap.setVisible(true);
			DWSwingWindowListener.getDefault().addWindow(minimap);
			return;
		}
		minimap = new DWSwingWindow("DWorld Mini Map", DWSwingWindow.ORIENTATION_RIGHT);
		minimap.setLayout(new GridLayout());
		
		JPanel panel = new JPanel(){
			static final long serialVersionUID = 16;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(DWConfiguration.getInstance().getControlledUnit() != null){
					drawRegion(g, 
							DWConfiguration.getInstance().getControlledUnit().getDrawPosition().getX()-150,
							DWConfiguration.getInstance().getControlledUnit().getDrawPosition().getY()-150,
							300,
							300,
							new NullProgressMonitor());
				}
			}
		};
		panel.setBackground(Color.black);
		panel.setSize(300, 300);
		panel.setPreferredSize(minimap.getSize());
		
		minimap.add(panel);
		minimap.pack();
		//minimap.setLocation(1300, 300);
		minimap.setSize(320, 320);
		minimap.setFocusableWindowState(false);
		minimap.setFocusable(false);
		minimap.setVisible(true);
		DWSwingWindowListener.getDefault().addWindow(minimap);
	}
	
	static void createImage(){
		BufferedImage image = new BufferedImage(Land.getMaxX(), Land.getMaxY(), BufferedImage.TYPE_INT_ARGB);
		LongRunningTask task = new LongRunningTask( m -> {
			Graphics g = image.createGraphics();
			drawRegion(g, 0, 0, Land.getMaxX(), Land.getMaxY(), m);
		} );
		DWSwingProgressMonitor monitor = new DWSwingProgressMonitor("Map creating...");
		task.addPropertyChangeListener(new PropertyChangeListener() {
		      @Override
		      public void propertyChange(final PropertyChangeEvent event) {
		        if ("progress".equals(event.getPropertyName())) {
		        	if(monitor.isCancelled()){
		        		task.cancel(true);
		        	}else{
		        		monitor.progress((Integer) event.getNewValue());
		        	}
		        }
		        if (StateValue.DONE == task.getState()){
		        	monitor.close();
		        	if(!task.isCancelled()){
		        		DWSwingMap.image = image;
		        		doMap();
		        	}
		        }
		      }
		});
		task.execute();
	}
	
	static void drawRegion(Graphics g, int startX, int startY, int width, int height, IProgressMonitor monitor){
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
				if(land == Land.Empty){
					land = Land.getBackground(x, y);
				}
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
						
						g.setColor(Color.red);
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
						
						g.setColor(Color.gray);
						break;
						
					case Grass:
						g.setColor(Color.green);
						break;
						
					case Water:
						g.setColor(Color.blue);
						break;
						
					case Sand:
						g.setColor(Color.yellow);
						break;
					case Mountain:
						g.setColor(Color.magenta);
						break;
					case Wood1:
					case Wood2:
					case Wood3:
					case Wood4:
						g.setColor(Color.orange);
						break;
						
					case Tree1:
					case Tree2:
					case Tree3:
						g.setColor(new Color(0,100,0));
						break;

					case Ammo:
					case Grenade:
					case Rocket:
						g.setColor(Color.pink);
						break;
						
						default:
							g.setColor(Color.white);
					}
					g.drawLine(windowX, windowY, windowX, windowY);
				}
			}
		}
	}
}

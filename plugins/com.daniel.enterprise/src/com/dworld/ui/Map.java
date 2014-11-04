package com.dworld.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import com.dworld.DWorldLauncher;
import com.dworld.core.Land;

public class Map {
	public static void showMap(){
		final JFrame window = new JFrame();
		window.setTitle("Map");
		window.setLayout(new GridLayout());
		
		JPanel panel = new JPanel(){
			static final long serialVersionUID = 15;

			public void paint(Graphics g) {
				super.paint(g);
				
				drawRegion(g, 0, 0, Land.getMaxX(), Land.getMaxY());
			}
		};
		panel.setBackground(Color.black);
		panel.setSize(Land.getMaxX(), Land.getMaxY());
		panel.setPreferredSize(panel.getSize());
		
		JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.getVerticalScrollBar().setUnitIncrement(30);
		scroll.getHorizontalScrollBar().setUnitIncrement(30);
		
		//scroll.add(panel);
		
		window.add(scroll);
		window.pack();
		scroll.getVerticalScrollBar().setValue(750);
		scroll.getHorizontalScrollBar().setValue(600);
		window.setLocation(0, 0);
		window.setSize(1600, 870);
		window.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == 27) {
					window.dispose();
				}
			}
			public void keyReleased(KeyEvent event) {
			}
			public void keyTyped(KeyEvent event) {
			}
		});
		window.setVisible(true);
	}
	
	static JFrame minimap = null;
	static Point drawPoint = null;
	
	public static void refreshMinimap(){
		if(minimap != null){
			if(drawPoint == null)
				drawPoint = new Point(DWorldLauncher.getControlledUnit().getDrawPosition());
			else{
				if(!DWorldLauncher.getControlledUnit().getDrawPosition().equals(drawPoint)){
					minimap.repaint();
					drawPoint = new Point(DWorldLauncher.getControlledUnit().getDrawPosition());
				}
			}
			
		}
	}
	
	public static void switchMinimap(){
		if(minimap == null)
			showMinimap();
		else
			closeMinimap();
	}
	
	public static void closeMinimap(){
		if(minimap != null){
			minimap.setVisible(false);
			minimap = null;
		}
	}
	
	public static void showMinimap(){
		minimap = new JFrame();
		minimap.setTitle("Mini Map");
		minimap.setLayout(new GridLayout());
		
		JPanel panel = new JPanel(){
			static final long serialVersionUID = 16;

			public void paint(Graphics g) {
				super.paint(g);
				if(DWorldLauncher.getControlledUnit() != null)
					drawRegion(g, DWorldLauncher.getControlledUnit().getDrawPosition().x-150, DWorldLauncher.getControlledUnit().getDrawPosition().y-150, 300, 300);
			}
		};
		panel.setBackground(Color.black);
		panel.setSize(300, 300);
		panel.setPreferredSize(minimap.getSize());
		
		minimap.add(panel);
		minimap.pack();
		minimap.setLocation(1000, 300);
		minimap.setSize(320, 320);
		minimap.setFocusableWindowState(false);
		minimap.setFocusable(false);
		minimap.setVisible(true);
		
	}
	
	public static void drawRegion(Graphics g, int startX, int startY, int width, int height){
		
		for(int x = startX, windowX = 0; x < (startX+width); x++, windowX++){
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
						
						g.setColor(Color.red);
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
						
						g.setColor(Color.gray);
						break;
						
					case Land.Grass:
						g.setColor(Color.green);
						break;
						
					case Land.Water:
						g.setColor(Color.blue);
						break;
						
					case Land.Sand:
						g.setColor(Color.yellow);
						break;
						
					case Land.Wood1:
					case Land.Wood2:
					case Land.Wood3:
					case Land.Wood4:
						g.setColor(Color.orange);
						break;
						
					case Land.Tree1:
					case Land.Tree2:
					case Land.Tree3:
						g.setColor(new Color(0,100,0));
						break;

					case Land.Ammo:
					case Land.Grenada:
					case Land.Rocket:
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

package com.dworld.ui.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.DWEngine;
import com.dworld.core.IActive;
import com.dworld.core.Land;
import com.dworld.units.ControlledUnit;
import com.dworld.units.Gate;
import com.dworld.units.Teleport;
import com.dworld.units.Unit;
import com.dworld.units.citizens.GoodBunker;
import com.dworld.units.citizens.GoodGeneral;
import com.dworld.units.citizens.GoodOfficer;
import com.dworld.units.citizens.GoodRadar;
import com.dworld.units.citizens.GoodSoldier;
import com.dworld.units.citizens.GoodTank;
import com.dworld.units.enemies.BadBunker;
import com.dworld.units.enemies.BadGeneral;
import com.dworld.units.enemies.BadOfficer;
import com.dworld.units.enemies.BadRadar;
import com.dworld.units.enemies.BadSoldier;
import com.dworld.units.enemies.BadTank;
import com.dworld.units.railroad.Train;
import com.dworld.units.weapon.Bomb;
import com.dworld.units.weapon.Bullet;
import com.dworld.units.weapon.CannonBall;
import com.dworld.units.weapon.Mine;
import com.dworld.units.weapon.Rocket;

public class DWInfoScreen extends JDialog{
	public static final long serialVersionUID = 2;
	private JLabel label;
	

	public DWInfoScreen(){
		super(((DWSwingUI)DWConfiguration.getInstance().getUI()).getWindow());
		
		setResizable(false);
		setTitle("Info Screen");
		String text = getInfo();
		label = new JLabel(text);
		label.setFont(new Font("Serif", Font.PLAIN, 12));

		label.setVerticalAlignment(SwingConstants.TOP);
		label.setPreferredSize(new Dimension(500, 600));
		add(label);
		pack();
		setLocation(getParent().getLocation().x+getParent().getSize().width/2-getSize().width/2, getParent().getLocation().y+getParent().getSize().height/2-getSize().height/2);
		addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == 27) {
					dispose();
				}
			}
			public void keyReleased(KeyEvent event) {
			}
			public void keyTyped(KeyEvent event) {
			}
		});
		setVisible(true);
	}
	
	private String getInfo(){
		ControlledUnit controlledUnit = DWConfiguration.getInstance().getControlledUnit();
		StringBuffer buffer = new StringBuffer();
		DWEngine engine = DWConfiguration.getInstance().getEngine();
		
		buffer.append("<html>");
		
		buffer.append("<center><table border=0 width=300px>");
		buffer.append("<tr><td><b>Map size:</b></td><td align=\"right\">"+Land.getMaxX()+" x "+Land.getMaxY()+"</td></tr>");
		buffer.append("<tr><td><b>Screen size:</b></td><td align=\"right\">"+DWConstants.UI_WIDTH+" x "+DWConstants.UI_HEIGHT+"</td></tr>");
		buffer.append("<tr><td><b>Hero position:</b></td><td align=\"right\">"+controlledUnit.getLocation().getX()+", "+controlledUnit.getLocation().getY()+"</td></tr>");
		buffer.append("<tr><td><b>Viewport position:</b></td><td align=\"right\">"+controlledUnit.getDrawPosition().getX()+", "+controlledUnit.getDrawPosition().getY()+"</td></tr>");
		buffer.append("<tr><td><b>Number of active units:</b></td><td align=\"right\">"+engine.getNumberOfActiveUnits()+" / "+engine.getMaxNumber()+"</td></tr>");
		buffer.append("<tr><td><b>Number of units:</b></td><td align=\"right\">"+(engine.getNumberOfActiveUnits()+engine.getNumberOfSlowUnits())+"</td></tr>");
		buffer.append("<tr><td><b>Total Number of used units:</b></td><td align=\"right\">"+Unit.getUnits()+"</td></tr>");
		buffer.append("<tr><td><b>Loop time:</b></td><td align=\"right\">"+engine.getCurrentDelta()+" / "+engine.getMaxDelta()+"</td></tr>");
		buffer.append("</table></center>");
		
		int trains = 0;
		int mines = 0;
		int bullets = 0;
		int cannonBalls = 0;
		int bombs = 0;
		int rockets = 0;
		
		int doors = 0;
		int teleports = 0;
		
		int robots = 0;
		int badOficers = 0;
		int badGenerals = 0;
		int tanks = 0;
		int bunkers = 0;
		int radars = 0;
		
		int soldiers = 0;
		int officers = 0;
		int generals = 0;
		int gTanks = 0;
		int dots = 0;
		int patriots = 0;
		
		for(int i = 0; i < engine.getNumberOfActiveUnits(); i++){
			IActive element = engine.getElement(i);
			if(element instanceof Mine)
				mines++;
			else if(element instanceof Bullet)
				bullets++;
			else if(element instanceof CannonBall)
				cannonBalls++;
			else if(element instanceof Bomb)
				bombs++;
			else if(element instanceof Rocket)
				rockets++;
			else if(element instanceof BadGeneral)
				badGenerals++;
			else if(element instanceof BadOfficer)
				badOficers++;
			else if(element instanceof BadSoldier)
				robots++;
			else if(element instanceof BadTank)
				tanks++;
			else if(element instanceof BadBunker)
				bunkers++;
			else if(element instanceof GoodGeneral)
				generals++;
			else if(element instanceof GoodOfficer)
				officers++;
			else if(element instanceof GoodSoldier)
				soldiers++;
			else if(element instanceof GoodTank)
				gTanks++;
			else if(element instanceof GoodBunker)
				dots++;
			else if(element instanceof Train)
				trains++;
		}
		
		for(int i = 0; i < engine.getNumberOfSlowUnits(); i++){
			IActive element = engine.getSlowElement(i);
			if(element instanceof Gate)
				doors++;
			else if(element instanceof Teleport)
				teleports++;
			else if(element instanceof GoodRadar)
				patriots++;
			else if(element instanceof BadRadar)
				radars++;
		}
		
		buffer.append("<center><table border=0 width=100px>");
		
		buffer.append("<tr><td colspan=2><b>Infrostructure</b></td></tr>");
		
		buffer.append("<tr><td>Doors:</td><td align=\"right\">"+doors+"</td></tr>");
		buffer.append("<tr><td>Teleports:</td><td align=\"right\">"+teleports+"</td></tr>");
		buffer.append("<tr><td>Trains:</td><td align=\"right\">"+trains+"</td></tr>");
		
		buffer.append("<tr><td colspan=2><b>Weapons</b></td></tr>");
		
		buffer.append("<tr><td>Bullets:</td><td align=\"right\">"+bullets+"</td></tr>");
		buffer.append("<tr><td>Cannon balls:</td><td align=\"right\">"+cannonBalls+"</td></tr>");
		buffer.append("<tr><td>Bombs:</td><td align=\"right\">"+bombs+"</td></tr>");
		buffer.append("<tr><td>Rockets:</td><td align=\"right\">"+rockets+"</td></tr>");
		buffer.append("<tr><td>Mines:</td><td align=\"right\">"+mines+"</td></tr>");
		buffer.append("</table></center>");
		
		buffer.append("<center><table border=1 width=300px>");
		buffer.append("<tr><td></td><td><b>Blue Forces</b></td><td><b>Gray Forces</b></td><td><b>Green Forces</b></td><td><b>Red Forces</b></td></tr>");
		
		buffer.append("<tr><td><b>Soldiers:</b></td><td align=\"right\">"+soldiers+"</td><td align=\"right\">"+robots+"</td><td align=\"right\">0</td><td align=\"right\">0</td></tr>");
		buffer.append("<tr><td><b>Officers:</b></td><td align=\"right\">"+officers+"</td><td align=\"right\">"+badOficers+"</td><td align=\"right\">0</td><td align=\"right\">0</td></tr>");
		buffer.append("<tr><td><b>Generals:</b></td><td align=\"right\">"+generals+"</td><td align=\"right\">"+badGenerals+"</td><td align=\"right\">0</td><td align=\"right\">0</td></tr>");
		buffer.append("<tr><td><b>Tanks:</b></td><td align=\"right\">"+gTanks+"</td><td align=\"right\">"+tanks+"</td><td align=\"right\">0</td><td align=\"right\">0</td></tr>");
		buffer.append("<tr><td><b>Bunkers:</b></td><td align=\"right\">"+dots+"</td><td align=\"right\">"+bunkers+"</td><td align=\"right\">0</td><td align=\"right\">0</td></tr>");
		buffer.append("<tr><td><b>Radars:</b></td><td align=\"right\">"+patriots+"</td><td align=\"right\">"+radars+"</td><td align=\"right\">0</td><td align=\"right\">0</td></tr>");
		//buffer.append("<tr><td><b>Cannons:</b></td><td align=\"right\">0</td><td align=\"right\">0</td><td align=\"right\">0</td><td align=\"right\">0</td></tr>");
		buffer.append("</table></center>");
		
		buffer.append("</html>");
		
		return buffer.toString();
	}
}

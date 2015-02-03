package com.daniel.graphics;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dworld.core.DWConstants;
import com.dworld.ui.DWMap;

public class ShowGraphics{
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setTitle("Graphics");
		window.setIconImage(new ImageIcon("resources/land/patriot.gif")
				.getImage());
		JPanel panel = new JPanel() {
			static final long serialVersionUID = 12;

			public void paint(Graphics g) {
				//g.draw
			}
		};
		
		panel.setSize(DWConstants.UI_WIDTH * DWConstants.UI_IMAGE_WIDTH, DWConstants.UI_HEIGHT * DWConstants.UI_IMAGE_HEIGHT);
		window.setLayout(new BorderLayout());
		window.add(panel, BorderLayout.CENTER);
		//initToolBar();
		window.pack();
		window.setSize(DWConstants.UI_WIDTH * DWConstants.UI_IMAGE_WIDTH + 8,
				DWConstants.UI_HEIGHT * DWConstants.UI_IMAGE_HEIGHT + 48);
		window.setLocation(200, 10);
		//window.addKeyListener(launcher);
		//panel.addMouseListener(launcher);
		//panel.addMouseMotionListener(launcher);
		//window.addFocusListener(launcher);
		window.setResizable(false);
		window.setVisible(true);
		window.setFocusable(true);
		window.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent arg0) {
			}

			public void windowClosed(WindowEvent arg0) {
			}

			public void windowClosing(WindowEvent arg0) {
				//exitConfirmation();
				System.exit(0);
			}

			public void windowDeactivated(WindowEvent arg0) {
			}

			public void windowDeiconified(WindowEvent arg0) {
			}

			public void windowIconified(WindowEvent arg0) {
			}

			public void windowOpened(WindowEvent arg0) {
			}
		});
		
		DWMap.switchMinimap();

	}
}

package com.dworld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

import com.dworld.core.DWConfiguration;
import com.dworld.core.DWConstants;
import com.dworld.core.DWEngine;
import com.dworld.core.ILauncher;
import com.dworld.ui.DWMessage;
import com.dworld.ui.IMonitoredRunnable;
import com.dworld.ui.IProgressMonitor;
import com.dworld.ui.LoadAction;
import com.dworld.ui.SaveAction;
import com.dworld.ui.swing.DWSwingImages;
import com.dworld.ui.swing.DWSwingMap;
import com.dworld.ui.swing.DWSwingMenuBuilder;
import com.dworld.ui.swing.DWSwingProgressMonitor;
import com.dworld.ui.swing.DWSwingToolbarBuilder;
import com.dworld.ui.swing.DWSwingUI;
import com.dworld.ui.swing.DWSwingWindowListener;

public class DWSwingLauncher implements KeyListener, MouseListener, MouseMotionListener, ILauncher {
	private JFrame window;
	private JPanel panel = null;
	
	private DWEngine engine;
	
	DWConfiguration configuration;

	public DWSwingLauncher() {

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			DWSwingLauncher launcher = new DWSwingLauncher();

			launcher.init();
		
			launcher.start();
		});
	}
	
	private void init() {
		String pathName = "";
		File jar = new File(DWSwingLauncher.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		if(jar.exists()){
			pathName = jar.getParent();
		}
		configuration = DWConfiguration.getInstance();
		configuration.setLauncher(this);
		
		window = DWConfiguration.getInstance().getUI(DWSwingUI.class).getWindow();
		engine = configuration.getEngine();
		
		configuration.setPathName(pathName);
		DWSwingWindowListener.getDefault().addMainWindow(window);
		initMenu();
		initWindow();
	}

	private void start() {
		load(DWConfiguration.SAVE_FILE);
		engine.init();
		
		new Thread(engine, "DW Engine").start();
	}

	private void initWindow() {

		window.setTitle(DWMessage.TITLE.get());
		window.setIconImage(new ImageIcon("resources/land/patriot.gif")
				.getImage());
		panel = new JPanel() {
			static final long serialVersionUID = 12;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				DWConfiguration.getInstance().getUI().getImages(DWSwingImages.class).draw(g);
			}
		};
		panel.setOpaque(true);
		panel.setBackground(Color.black);
		
		panel.setSize(DWConstants.UI_WIDTH * DWConstants.UI_IMAGE_WIDTH, DWConstants.UI_HEIGHT * DWConstants.UI_IMAGE_HEIGHT);
		window.setLayout(new BorderLayout());
		window.add(panel, BorderLayout.CENTER);
		initToolBar();
		window.pack();
		window.setSize(DWConstants.UI_WIDTH * DWConstants.UI_IMAGE_WIDTH + 8,
				DWConstants.UI_HEIGHT * DWConstants.UI_IMAGE_HEIGHT + 48);
		window.setLocation(480, 10);
		window.addKeyListener(this);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		window.setResizable(false);
		window.setVisible(true);
		window.setFocusable(true);
		window.setFocusableWindowState(true);
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				DWConfiguration.getInstance().getUI().exitConfirmation();
				System.exit(0);
			}
		});
		
		DWSwingMap.toggleMinimap();
	}
	
	private void initMenu() {
		DWSwingMenuBuilder menuBuilder = new DWSwingMenuBuilder();
		
		
		window.setJMenuBar(menuBuilder.buildMenu());
	}
	
	private void initToolBar(){
		DWSwingToolbarBuilder toolBarBuilder = new DWSwingToolbarBuilder();
		JToolBar toolBar = toolBarBuilder.buildToolBar();
		
		window.add(toolBar, BorderLayout.NORTH);
	}
	
	
	@Override
	public void keyPressed(KeyEvent event) {
//		System.out.println("keyPressed");
//		System.out.println("Char - "+event.getKeyChar());
//		System.out.println("Code - "+event.getKeyCode());
//		System.out.println("Modifiers - "+event.getModifiers());
//		System.out.println("ModifiersExt - "+event.getModifiersEx());
		DWConfiguration.getInstance().getUI().getKeyListener().doKeyPressed(event.getKeyCode(), event.getModifiers());
	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}


	@Override
	public void mousePressed(MouseEvent e) {
		DWConfiguration.getInstance().getUI().getMouseListener().doMousePressed(e.getButton(), e.getX(), e.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		DWConfiguration.getInstance().getUI().getMouseListener().doMouseReleased(e.getButton(), e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		DWConfiguration.getInstance().getUI().getMouseListener().doMouseDragged(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void load(String fileName) {
		LongRunningTask task = new LongRunningTask(new LoadAction(fileName));
		DWSwingProgressMonitor monitor = new DWSwingProgressMonitor(DWMessage.LOADING.get());
		
		task.addPropertyChangeListener(event -> {
	        if ("progress".equals(event.getPropertyName())) {
	        	if(monitor.isCancelled()){
	        		task.cancel(true);
	        	}else{
	        		monitor.progress((Integer) event.getNewValue());
	        	}
	        }
	        if (StateValue.DONE == task.getState()){
	        	monitor.close();
	        }
		});
		task.execute();
	}

	@Override
	public void save(String fileName) {
		LongRunningTask task = new LongRunningTask(new SaveAction(fileName));
		DWSwingProgressMonitor monitor = new DWSwingProgressMonitor(DWMessage.SAVING.get());
		task.addPropertyChangeListener(event -> {
	        if ("progress".equals(event.getPropertyName())) {
	        	monitor.progress((Integer) event.getNewValue());
	        }
	        if (StateValue.DONE == task.getState()){
	        	monitor.close();
	        }
		});
		task.execute();
	}

	@Override
	public void saveAndExit(String fileName) {
		LongRunningTask task = new LongRunningTask(new SaveAction(fileName));
		DWSwingProgressMonitor monitor = new DWSwingProgressMonitor(DWMessage.SAVING.get());
		task.addPropertyChangeListener(event -> {
	        if ("progress".equals(event.getPropertyName())) {
	        	monitor.progress((Integer) event.getNewValue());
	        }
	        if (StateValue.DONE == task.getState()){
	        	monitor.close();
	        }
		});
		task.execute();
	}
	
	static public class LongRunningTask extends SwingWorker<Integer, Integer> implements IProgressMonitor{
		IMonitoredRunnable runner;
		
		public LongRunningTask(IMonitoredRunnable runner){
			this.runner = runner;
		}

		@Override
		protected Integer doInBackground() throws Exception {
			runner.run(this);
			
			return 1;
		}

		@Override
		public void progress(int progress) {
			setProgress(progress);
		}

		@Override
		public void close() {
		}

	}
}

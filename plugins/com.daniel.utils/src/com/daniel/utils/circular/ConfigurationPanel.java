package com.daniel.utils.circular;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.daniel.utils.swing.SwingFactory;

public class ConfigurationPanel extends JPanel {
	private static final long serialVersionUID = -6661477508212953938L;
	private Configuration configuration;
	private VisualPanel visualPanel;
	
	public ConfigurationPanel(Configuration configuration, VisualPanel visualPanel) {
		super();
		this.configuration = configuration;
		this.visualPanel = visualPanel;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(SwingFactory.createTextField("Angle: ",
				"" + configuration.getAngle(), 500,
				v -> configuration.setAngle(Integer.valueOf(v))));
		
		add(SwingFactory.createTextField("Step: ",
						"" + configuration.getStep(), 500,
						v -> configuration.setStep(Integer.valueOf(v))));
		
		add(SwingFactory.createTextField("Opposite Angle: ",
								"" + configuration.getOppositAngle(), 500,
								v -> configuration.setOppositAngle(Integer.valueOf(v))));
		
		add(SwingFactory.createTextField("BaseX: ",
				"" + configuration.getBaseX(), 500,
				v -> configuration.setBaseX(Integer.valueOf(v))));
		
		add(SwingFactory.createTextField("BaseY: ",
				"" + configuration.getBaseY(), 350,
				v -> configuration.setBaseY(Integer.valueOf(v))));
		
		add(SwingFactory.createTextField("RateX: ",
				"" + configuration.getRateX(), 300,
				v -> configuration.setRateX(Integer.valueOf(v))));
		
		add(SwingFactory.createTextField("RateY: ",
				"" + configuration.getRateY(), 300,
				v -> configuration.setRateY(Integer.valueOf(v))));
		
		JButton removeButton = new JButton("Refresh");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				visualPanel.repaint();
			}
		});
		add(removeButton);
	}
}

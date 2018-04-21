package com.daniel.utils.mathvisual;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.daniel.utils.mathvisual.Configuration.GraphConfiguration;

public class ConfigurationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Configuration configuration;
	private HashMap<JTextField, ConfigurationUpdater> bindingMap = new HashMap<>();

	public ConfigurationPanel(Configuration configuration) {
		super();
		this.configuration = configuration;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel startPanel = new JPanel();
		startPanel.add(new JLabel("Start: "));
		JTextField startField = new JTextField("" + configuration.getStart(),
				40);
		startField.setMaximumSize(startField.getPreferredSize());
		bindingMap.put(startField,
				v -> configuration.setStart(Double.valueOf(v)));
		startPanel.add(startField);
		add(startPanel);

		JPanel endPanel = new JPanel();
		endPanel.add(new JLabel("End: "));
		JTextField endField = new JTextField("" + configuration.getEnd(), 40);
		endField.setMaximumSize(endField.getPreferredSize());
		bindingMap.put(endField,
				v -> configuration.setEnd(Double.valueOf(v)));
		endPanel.add(endField);
		add(endPanel);

		JPanel numPanel = new JPanel();
		numPanel.add(new JLabel("Number: "));
		JTextField numberField = new JTextField("" + configuration.getNumber(),
				40);
		numberField.setMaximumSize(numberField.getPreferredSize());
		bindingMap.put(numberField,
				v -> configuration.setNumber(Integer.valueOf(v)));
		numPanel.add(numberField);
		add(numPanel);
		
		JPanel minYPanel = new JPanel();
		minYPanel.add(new JLabel("Min Y: "));
		JTextField minYField = new JTextField("" + configuration.getMinY(),
				40);
		minYField.setMaximumSize(minYField.getPreferredSize());
		bindingMap.put(minYField,
				v -> configuration.setMinY(Double.valueOf(v)));
		minYPanel.add(minYField);
		add(minYPanel);
		
		JPanel maxYPanel = new JPanel();
		maxYPanel.add(new JLabel("Max Y: "));
		JTextField maxYField = new JTextField("" + configuration.getMaxY(),
				40);
		maxYField.setMaximumSize(maxYField.getPreferredSize());
		bindingMap.put(maxYField,
				v -> configuration.setMaxY(Double.valueOf(v)));
		maxYPanel.add(maxYField);
		add(maxYPanel);

		JButton addGraphButton = new JButton("Add Graph");

		addGraphButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add(new GraphConfPanel(configuration.addGraph()));
				updateUI();
			}
		});
		add(addGraphButton);

		for (GraphConfiguration gc : configuration.getGraphConfigs()) {
			add(new GraphConfPanel(gc));
		}
	}

	public void updateConfiguration() {
		for (JTextField field : bindingMap.keySet()) {
			ConfigurationUpdater updater = bindingMap.get(field);
			try {
				updater.update(field.getText());
			} catch (Exception ex) {
				System.out.println(
						"Error while updating configuration property!");
			}
		}
	}

	class GraphConfPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public GraphConfPanel(GraphConfiguration gc) {
			super();

			add(new JLabel("Visible: "));
			JCheckBox check = new JCheckBox();
			check.setSelected(gc.isVisible());
			check.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gc.setVisible(check.isSelected());
				}
			});
			add(check);

			add(new JLabel("Formula: "));
			JTextField formulaField = new JTextField(gc.getFormula(), 40);
			bindingMap.put(formulaField, v -> gc.setFormula(v));
			add(formulaField);

			JButton colorButton = new JButton("Color");
			colorButton.setBackground(gc.getColor());
			colorButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(
							ConfigurationPanel.this, "Choose Graph Color",
							gc.getColor());
					gc.setColor(newColor);
					colorButton.setBackground(gc.getColor());
				}
			});
			add(colorButton);

			JButton removeButton = new JButton("Remove");
			removeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					configuration.removeGraph(gc);
					ConfigurationPanel.this.remove(GraphConfPanel.this);
					ConfigurationPanel.this.updateUI();
				}
			});
			add(removeButton);
		}
	}
}

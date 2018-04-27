package com.daniel.utils.mathvisual;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.daniel.utils.mathvisual.Configuration.GraphConfiguration;
import com.daniel.utils.swing.SwingFactory;

public class ConfigurationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Configuration configuration;

	public ConfigurationPanel(Configuration configuration) {
		super();
		this.configuration = configuration;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel colorPanel = new JPanel();
		colorPanel.add(
				SwingFactory.createColorChooserButton(ConfigurationPanel.this,
						"Background Color", configuration.getBackgroundColor(),
						c -> configuration.setBackgroundColor(c)));

		colorPanel.add(
				SwingFactory.createColorChooserButton(ConfigurationPanel.this,
						"Grid Color", configuration.getGridColor(),
						c -> configuration.setGridColor(c)));

		JButton textButton = SwingFactory.createColorChooserButton(
				ConfigurationPanel.this, "Text Color",
				configuration.getTextColor(),
				c -> configuration.setTextColor(c));
		textButton.setForeground(Color.WHITE);
		colorPanel.add(textButton);
		add(colorPanel);

		add(SwingFactory.createTextField("Start: ",
				"" + configuration.getStart(), 40,
				v -> configuration.setStart(Double.valueOf(v))));

		add(SwingFactory.createTextField("End: ", "" + configuration.getEnd(),
				40, v -> configuration.setEnd(Double.valueOf(v))));

		add(SwingFactory.createTextField("Number: ",
				"" + configuration.getNumber(), 40,
				v -> configuration.setNumber(Integer.valueOf(v))));

		add(SwingFactory.createTextField("Min Y: ",
				"" + configuration.getMinY(), 40,
				v -> configuration.setMinY(Double.valueOf(v))));

		add(SwingFactory.createTextField("Max Y: ",
				"" + configuration.getMaxY(), 40,
				v -> configuration.setMaxY(Double.valueOf(v))));

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

			JPanel textPanel = new JPanel();
			textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));

			textPanel.add(SwingFactory.createTextField("Formula: ",
					gc.getFormula(), 40, v -> gc.setFormula(v)));

			textPanel.add(SwingFactory.createTextField("Comment: ",
					gc.getComment(), 40, v -> gc.setComment(v)));
			add(textPanel);

			add(SwingFactory.createColorChooserButton(ConfigurationPanel.this,
					"Color", gc.getColor(), c -> gc.setColor(c)));

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

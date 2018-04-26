package com.daniel.utils.mathvisual;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.daniel.utils.mathvisual.Configuration.GraphConfiguration;

public class ConfigurationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Configuration configuration;

	public ConfigurationPanel(Configuration configuration) {
		super();
		this.configuration = configuration;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel colorPanel = new JPanel();
		JButton bcButton = new JButton("Background Color");
		bcButton.setBackground(configuration.getBackgroundColor());
		addUpdater(bcButton, c -> configuration.setBackgroundColor(c));
		colorPanel.add(bcButton);
		
		JButton gridButton = new JButton("Grid Color");
		gridButton.setBackground(configuration.getGridColor());
		addUpdater(gridButton, c -> configuration.setGridColor(c));
		colorPanel.add(gridButton);
		add(colorPanel);
		
		JButton textButton = new JButton("Text Color");
		textButton.setForeground(Color.WHITE);
		textButton.setBackground(configuration.getTextColor());
		addUpdater(textButton, c -> configuration.setTextColor(c));
		colorPanel.add(textButton);
		add(colorPanel);

		JPanel startPanel = new JPanel();
		startPanel.add(new JLabel("Start: "));
		JTextField startField = new JTextField("" + configuration.getStart(),
				40);
		startField.setMaximumSize(startField.getPreferredSize());
		addUpdater(startField,
				v -> configuration.setStart(Double.valueOf(v)));
		startPanel.add(startField);
		add(startPanel);

		JPanel endPanel = new JPanel();
		endPanel.add(new JLabel("End: "));
		JTextField endField = new JTextField("" + configuration.getEnd(), 40);
		endField.setMaximumSize(endField.getPreferredSize());
		addUpdater(endField,
				v -> configuration.setEnd(Double.valueOf(v)));
		endPanel.add(endField);
		add(endPanel);

		JPanel numPanel = new JPanel();
		numPanel.add(new JLabel("Number: "));
		JTextField numberField = new JTextField("" + configuration.getNumber(),
				40);
		numberField.setMaximumSize(numberField.getPreferredSize());
		addUpdater(numberField,
				v -> configuration.setNumber(Integer.valueOf(v)));
		numPanel.add(numberField);
		add(numPanel);
		
		JPanel minYPanel = new JPanel();
		minYPanel.add(new JLabel("Min Y: "));
		JTextField minYField = new JTextField("" + configuration.getMinY(),
				40);
		minYField.setMaximumSize(minYField.getPreferredSize());
		addUpdater(minYField,
				v -> configuration.setMinY(Double.valueOf(v)));
		minYPanel.add(minYField);
		add(minYPanel);
		
		JPanel maxYPanel = new JPanel();
		maxYPanel.add(new JLabel("Max Y: "));
		JTextField maxYField = new JTextField("" + configuration.getMaxY(),
				40);
		maxYField.setMaximumSize(maxYField.getPreferredSize());
		addUpdater(maxYField,
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
	
	private void addUpdater(JTextField field, Consumer<String> updater){
		field.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}
			
			private void update(){
				try{
					updater.accept(field.getText());
				}catch(Exception ex){
					System.out.println("Error while updating the configuration property!");
				}
			}
		});
	}
	
	private void addUpdater(JButton button, Consumer<Color> updater){
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(
						ConfigurationPanel.this, "Choose Color",
						configuration.getBackgroundColor());
				if(newColor != null){
					updater.accept(newColor);
					button.setBackground(newColor);
				}
			}
		});
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
			
			JPanel formulaPanel = new JPanel();
			formulaPanel.add(new JLabel("Formula: "));
			JTextField formulaField = new JTextField(gc.getFormula(), 40);
			addUpdater(formulaField, v -> gc.setFormula(v));
			formulaPanel.add(formulaField);
			textPanel.add(formulaPanel);
			
			JPanel commentPanel = new JPanel();
			commentPanel.add(new JLabel("Comment: "));
			JTextField commentField = new JTextField(gc.getComment(), 40);
			addUpdater(commentField, v -> gc.setComment(v));
			commentPanel.add(commentField);
			
			textPanel.add(commentPanel);
			add(textPanel);

			JButton colorButton = new JButton("Color");
			colorButton.setBackground(gc.getColor());
			addUpdater(colorButton, c -> gc.setColor(c));
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

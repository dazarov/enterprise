package com.daniel.utils.spring;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SpringFactory {
	public static JButton createColorChooserButton(Component component,
			String label, Color defaultColor, Consumer<Color> updater) {
		JButton button = new JButton(label);
		button.setBackground(defaultColor);
		addColorUpdater(component, button, defaultColor, updater);
		return button;
	}

	public static JPanel createTextField(String label, String defaultText,
			int columns, Consumer<String> updater) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(Box.createHorizontalStrut(20));
		panel.add(new JLabel(label));
		//panel.add(Box.createHorizontalGlue());
		JTextField field = new JTextField(defaultText, columns);
		field.setMaximumSize(field.getPreferredSize());
		addTextFieldUpdater(field, updater);
		panel.add(field);
		panel.add(Box.createHorizontalStrut(20));
		return panel;
	}

	private static void addTextFieldUpdater(JTextField field,
			Consumer<String> updater) {
		field.getDocument().addDocumentListener(new DocumentListener() {

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

			private void update() {
				try {
					updater.accept(field.getText());
				} catch (Exception ex) {
					System.out.println(
							"Error while updating the configuration property!");
				}
			}
		});
	}

	private static void addColorUpdater(Component component, JButton button,
			Color defaultColor, Consumer<Color> updater) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(component,
						"Choose Color", defaultColor);
				if (newColor != null) {
					updater.accept(newColor);
					button.setBackground(newColor);
				}
			}
		});
	}
}

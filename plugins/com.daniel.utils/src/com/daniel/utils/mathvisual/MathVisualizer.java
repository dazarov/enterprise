package com.daniel.utils.mathvisual;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.daniel.utils.mathvisual.Configuration.GraphConfiguration;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MathVisualizer {
	private Configuration configuration;
	private ConfigurationPanel configurationPanel;
	private VisualPanel visualPanel;

	public MathVisualizer() {
		JFrame frame = new JFrame();
		frame.setTitle("Math Formula Visualizer");
		JTabbedPane tabPane = new JTabbedPane();
		configuration = loadConfiguration();
		configurationPanel = new ConfigurationPanel(configuration);
		visualPanel = new VisualPanel();
		tabPane.addTab("Configuration", new JScrollPane(configurationPanel));
		tabPane.addTab("Visual View", visualPanel);

		tabPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabPane.getSelectedIndex() == 1) {
					configurationPanel.updateConfiguration();
					initView();
				}
			}
		});

		frame.add(tabPane);
		frame.pack();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				saveConfiguration();
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	private void initView() {
		GraphList list = new GraphList();
		list.setBackgroundColor(configuration.getBackgroundColor());
		list.setGridColor(configuration.getGridColor());
		double step = (configuration.getEnd() - configuration.getStart())
				/configuration.getNumber();
		for (GraphConfiguration gc : configuration.getGraphConfigs()) {
			if(!gc.isVisible()) continue;
			double[] x = new double[configuration.getNumber()];
			double[] y = new double[configuration.getNumber()];
			Expression calc = new ExpressionBuilder(gc.getFormula())
					.variable("x").build();
			for (int index = 0; index < configuration.getNumber(); index++) {
				if (index == 0) {
					x[index] = configuration.getStart();
				} else {
					x[index] = x[index - 1] + step;
				}
				calc.setVariable("x", x[index]);
				y[index] = calc.evaluate();
			}

			list.addGraph("", x, y, gc.getColor());
		}
		
		if(configuration.getMinY() != 0.0 || configuration.getMaxY() != 0.0){
			list.setMinY(configuration.getMinY());
			list.setMaxY(configuration.getMaxY());
		}

		visualPanel.init(list);
	}

	private Configuration loadConfiguration() {
		try (FileInputStream fis = new FileInputStream("MathVisualizer.cfg");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			return (Configuration) ois.readObject();
		} catch (Exception ex) {
			return new Configuration();
		}
	}

	private void saveConfiguration() {
		try (FileOutputStream fos = new FileOutputStream("MathVisualizer.cfg");
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(configuration);
			oos.flush();
		} catch (Exception ex) {
			System.out.println("Error while saving configuration!");
		}
	}

	public static void main(String[] args) {
		new MathVisualizer();
	}
}

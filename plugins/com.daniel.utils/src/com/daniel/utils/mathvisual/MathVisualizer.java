package com.daniel.utils.mathvisual;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.daniel.utils.mathvisual.Configuration.GraphConfiguration;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MathVisualizer {
	private static final String CONFIG_FILE_BIN = "MathVisualizer.cfg";
	private static final String CONFIG_FILE_XML = "MathVisualizer.xml";
	
	JAXBContext context;
	private Configuration configuration;
	private VisualPanel visualPanel;

	public MathVisualizer() {
		try {
			context = JAXBContext.newInstance(Configuration.class);
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		JFrame frame = new JFrame();
		frame.setTitle("Math Formula Visualizer");
		JTabbedPane tabPane = new JTabbedPane();
		configuration = loadConfigurationFromXML();
		visualPanel = new VisualPanel();
		tabPane.addTab("Configuration", new JScrollPane(new ConfigurationPanel(configuration)));
		tabPane.addTab("Visual View", visualPanel);

		tabPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabPane.getSelectedIndex() == 1) {
					initView();
				}
			}
		});

		frame.add(tabPane);
		frame.pack();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				saveConfigurationToXML();
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	private void initView() {
		GraphList list = new GraphList();
		list.setBackgroundColor(configuration.getBackgroundColor());
		list.setGridColor(configuration.getGridColor());
		list.setTextColor(configuration.getTextColor());
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

	@SuppressWarnings("unused")
	private Configuration loadConfigurationFromBin() {
		try (FileInputStream fis = new FileInputStream(CONFIG_FILE_BIN);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			return (Configuration) ois.readObject();
		} catch (Exception ex) {
			return new Configuration();
		}
	}

	@SuppressWarnings("unused")
	private void saveConfigurationToBin() {
		try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE_BIN);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(configuration);
			oos.flush();
		} catch (Exception ex) {
			System.out.println("Error while saving configuration!");
		}
	}
	
	private Configuration loadConfigurationFromXML() {
		try {
			Unmarshaller um = context.createUnmarshaller();
			return (Configuration) um.unmarshal(new FileReader(CONFIG_FILE_XML));
		} catch (FileNotFoundException | JAXBException e) {
			return new Configuration();
		}
        
	}
	
	private void saveConfigurationToXML() {
		try {
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(configuration, new File(CONFIG_FILE_XML));
		} catch (JAXBException e) {
			System.out.println("Error while saving configuration!");
		}
        
	}

	public static void main(String[] args) {
		new MathVisualizer();
	}
}

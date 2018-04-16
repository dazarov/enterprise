package com.daniel.utils.mathvisual;

import javax.swing.JPanel;

public class ConfigurationPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Expression calc = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
		    .variable("x", x)
		    .variable("y", y)
		    .build();
		double result1 = calc.evaluate();
}

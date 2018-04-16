package com.daniel.utils.mathvisual;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FormulaParser {
	private static final String DELIMS = "+-*/^()";
	private static List<String> operators = Stream.of("+","-","*","/","^","%").collect(Collectors.toList());
	private static List<String> functions = Stream.of("sin","cos","tan","atan","log","sqrt").collect(Collectors.toList());
	private double x;
	private String formula;
	Queue<Double> outputQueue = new LinkedList<Double>();
	Stack<String> operatorStack = new Stack<String>();
	
	public static double calculate(String formula, double x){
		FormulaParser parser = new FormulaParser(formula, x);
		return parser.parse();
	}
	
	public static void main(String[] args){
		FormulaParser parser = new FormulaParser("sin(12.24+16)*x", 1.0);
		double y = parser.parse();
	}
	
	public FormulaParser(String formula, double x){
		this.x = x;
		this.formula = formula;
	}
	
	private double parse(){
		StringTokenizer tokenizer = new StringTokenizer(formula, DELIMS, true);
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			System.out.println("Token - " + token);
			double a = 0.0;
			try{
			  a = Double.parseDouble(token);
			}catch(NumberFormatException ex){
				// do nothing
			}
			if(a != 0.0){
				outputQueue.add(a);
			}else if("x".equals(token)){
				outputQueue.add(x);
			}else if(functions.contains(token)){
				operatorStack.push(token);
			}else if(operators.contains(token)){
				
			}
		}
		
		
		return 0.0;
	}
}

package vr_net;

//import java.io.Closeable;
//import java.io.IOException;
import java.util.ArrayList;

import telran.view.InputOutput;
import telran.view.Item;

public class CalculatorMenuItems {
	private static Calculator calculator;
	
	static public ArrayList<Item> getCalculatorItems(Calculator calculator) {
		CalculatorMenuItems.calculator = calculator;
		ArrayList<Item> items = new ArrayList<>();
		items.add(Item.of("Add two numbers", CalculatorMenuItems::add));
		items.add(Item.of("Subtract two numbers", CalculatorMenuItems::subtract));
		items.add(Item.of("Multiply two numbers", CalculatorMenuItems::multiply));
		items.add(Item.of("Divide two numbers", CalculatorMenuItems::divide));
		items.add(Item.ofExit());
//		items.add(Item.of("Exit", CalculatorMenuItems::exitItem, true));
		
		return items;
	}
	static private double[] enterTwoNumbers(InputOutput io) {
		return new double[] {
				io.readDouble("Enter first number", "Has to be Double only"),
				io.readDouble("Enter second number", "Has to be Double only")
		};
	}
	static private void add(InputOutput io) {
		double[] numbers = enterTwoNumbers(io);
		io.writeLine(calculator.calculate("+", numbers[0], numbers[1]));
	}
	static private void subtract(InputOutput io) {
		double[] numbers = enterTwoNumbers(io);
		io.writeLine(calculator.calculate("-", numbers[0], numbers[1]));
	}
	static private void multiply(InputOutput io) {
		double[] numbers = enterTwoNumbers(io);
		io.writeLine(calculator.calculate("*", numbers[0], numbers[1]));
	}
	static private void divide(InputOutput io) {
		double[] numbers = enterTwoNumbers(io);
		io.writeLine(calculator.calculate("/", numbers[0], numbers[1]));
	}
//	static private void exitItem (InputOutput io) {
//		try {
//			((Closeable)calculator).close();
//		} catch(IOException ex) {
//			System.out.println(ex.getMessage());
//		}
//	}
}

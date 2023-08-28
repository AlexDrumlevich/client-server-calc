package OOP_server_client.impl;
import java.util.function.BiFunction;

import telran.view.Item;
import telran.view.Menu;

public class CalculatorMenu {

	static <R> Menu getCalculatorMenu(BiFunction<String, Double[], R> getResult) {
		return new Menu("TCP calculator", 
				Item.of("Send request", io -> {
					String requestType = io.readString("Enter operation type ");
					Double firstValue = io.readDouble("Enter first double value", "Wrong value");
					Double secondValue = io.readDouble("Enter second double value", "Wrong value");			
					io.writeLine(getResult.apply(requestType, new Double[] {firstValue, secondValue}));
		
				}),
				Item.of("Help", io -> {
					io.writeLine(getResult.apply("help", null));
				}),	 
				Item.ofExit());
	}
	
}

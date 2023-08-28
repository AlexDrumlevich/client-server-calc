package vr_net;

import java.util.ArrayList;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CalculatorClientAppl {
	private static final String HOST = "localhost";
	private static final int PORT = 2001;
	
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		try ( CalculatorProxy calculator = new CalculatorProxy(HOST, PORT)) {
			ArrayList<Item> items = CalculatorMenuItems.getCalculatorItems(calculator);		
			Menu menu = new Menu("Calculator", items);
			menu.perform(io);
		} catch (Exception ex) {
			io.writeLine(ex.getMessage());
		}
	}
}

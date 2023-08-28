package OOP_server_client.impl;

import OOP_server_client.client.TcpHandler;
import telran.view.ConsoleInputOutput;

public class ClientApp {

	public static void main(String[] args) {
		
		try(TcpHandler tcpHandler = new TcpHandler("localhost", 1000)) {
			CalculatorMenu.getCalculatorMenu(tcpHandler::send).perform(new ConsoleInputOutput());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package OOP_server_client.impl;

import java.io.IOException;

import OOP_server_client.server.TcpServer;

public class ServerApp {

	public static void main(String[] args) {
		
		try {
			new TcpServer(1000, new CalculatorProtocol()).run();
		} catch (IOException e) {
			System.out.println(e);
		}
		

	}

}

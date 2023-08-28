package vr_net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServerAppl {
	
	final static int PORT = 2001;
	static Calculator calculator = new CalculatorImpl();
	
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is listening on the port " + PORT);
			while(true) {
				Socket socket = serverSocket.accept();
				runClient(socket);
				System.out.println(socket.toString());
			}
		} catch (Exception ex) {
			System.out.println((ex.getMessage()));
		}
	}
	
	private static void runClient(Socket socket) {
		try(BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
				PrintStream writer = new PrintStream(socket.getOutputStream())) {
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					System.out.println("client closed connection normally");
					break;
				}	
				String response = getResponse(line);
				writer.println(response);
			}		
		} catch (Exception e) {
			System.out.println("client closed connection abnormally");
		}	
	}
	
	private static String getResponse(String request) {
		// <operation>#<operand1>#<operand2>
		// operation := "+" | "-" | "*" | "/"
		// operand1 | operand := double as String
		String [] tokens = request.split("#");
		if (tokens.length != 3) {
			return "Wrong Request";
		}
		try {
			double res = calculator.calculate(tokens[0], Double.parseDouble(tokens[1]),
				Double.parseDouble(tokens[2]));
			return "" + res;
		} catch (Exception e) {
			return e.getMessage();
		}	
	}
}

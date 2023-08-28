package vr_net;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class CalculatorProxy implements Calculator, Closeable {
	
	private PrintStream writer;
	private BufferedReader reader;
	private Socket socket;
	
	public CalculatorProxy(String host, int port) throws Exception{
		socket = new Socket(host, port);
		writer = new PrintStream(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public double calculate(String operator, double operand1, double operand2) {
		try {
			writer.println(String.format("%s#%f#%f", operator, operand1, operand2));
			
			return Double.parseDouble(reader.readLine());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void close() throws IOException {
		socket.close();
	}
}

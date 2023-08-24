package telran.client;
import java.net.*;
import java.util.*;
import java.io.*;
import telran.view.*;
public class TcpClient {
	static final String HOST="localhost";
	static final int PORT=6000;
	public static void main(String[] args) throws Exception {
		try(Socket socket = new Socket(HOST, PORT);
				PrintStream writer = new PrintStream(socket.getOutputStream());
				BufferedReader reader =
						new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			InputOutput io = new ConsoleInputOutput();
			Menu menu = new Menu("TCP calculator", 
					Item.of("Send request", io1 -> {
						String requestType = io1.readString("Enter operation type ");
						String firstValue = Double.valueOf(io1.readDouble("Enter first double value", "Wrong value")).toString();
						String secondValue = Double.valueOf(io1.readDouble("Enter second double value", "Wrong value")).toString();
						writer.println(String.format("%s#%s#%s", requestType, firstValue, secondValue));
						try {
							String response = reader.readLine();
							io1.writeLine(response);
						} catch (IOException e) {
							throw new RuntimeException(e.toString());
						}
					}),
					Item.of("Help", io1 -> {
						writer.println("help");
						String response;
						try {
							response = reader.readLine();
							io1.writeLine(response);
						} catch (IOException e) {
							throw new RuntimeException(e.toString());
						}
					}),	 
					Item.ofExit());
			menu.perform(io);
		}

	}

}

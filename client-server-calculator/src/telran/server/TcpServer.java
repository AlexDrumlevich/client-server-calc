package telran.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TcpServer {

	static final int PORT=6000;

	enum SupportedOperations {
		ADDITION, SUBSTRACT, MULTIPLICATION, DIVISION;

		public String  getResult(double value1, double value2) {
			String stringResult;
			stringResult = switch (this) {
			case ADDITION -> Double.toString(value1 + value2);
			case SUBSTRACT -> Double.toString(value1 - value2);
			case MULTIPLICATION -> Double.toString(value1 * value2);
			case DIVISION -> Double.toString(value1 / value2);
			};
		return stringResult;
		}
	}

	
		public static void main(String[] args) throws Exception{
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server is listening to port " + PORT);

			while(true) {
				Socket socket = serverSocket.accept();
				clientRun(socket);
			}

		}

		private static void clientRun(Socket socket) {
			try(BufferedReader reader =
					new BufferedReader
					(new InputStreamReader(socket.getInputStream()));
					PrintStream writer = new PrintStream(socket.getOutputStream())) {

				while(true) {
					String line = reader.readLine();
					if(line == null) {
						System.out.println("client closed normally connection");
						break;
					}
					String response = getResponse(line);
					writer.println(response);
				}

			} catch (Exception e) {
				System.out.println("client closed abnormally connection");
			}

		}

		private static String getResponse(String line) {
			// <operation type>#<first value>#<second value>
			String response = "";
			if(line.equals("help")) {
				response = "Request structure: <operation type>#<first value>#<second value>. Supported operation: " + Arrays.toString(SupportedOperations.values());
			} else {

				String [] tokens = line.split("#");
				if(tokens.length == 3) {
					SupportedOperations operation = null;
					Double firstValue = null;
					Double secondValue = null;
					try {
						firstValue = Double.valueOf(tokens[1]);
					} catch (Exception e) {
						response += e.toString();
					}
					try {
						secondValue = Double.valueOf(tokens[2]);
					} catch (Exception e) {
						response += e.toString();
					}
					try {
						operation = SupportedOperations.valueOf(tokens[0]);
					} catch (Exception e) {
						response += "Wrong operation type, supported operation: " + Arrays.toString(SupportedOperations.values());
					}

					if(firstValue != null && secondValue != null && operation != null) {
						response = operation.getResult(firstValue, secondValue);
					}
				} else {
					response = "Wrong request structure, usage: <request type>#<first value>#<second value>";
				}
			}
			return response;
		}
	}

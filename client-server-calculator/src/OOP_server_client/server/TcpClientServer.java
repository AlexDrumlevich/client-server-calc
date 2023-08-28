package OOP_server_client.server;

import java.net.*;

import OOP_server_client.response_request.Request;
import OOP_server_client.response_request.Response;

import java.io.*;

public class TcpClientServer implements Runnable {
	Socket socket;
	ObjectInputStream input;
	ObjectOutputStream output;
	ApplProtocol protocol;
	public TcpClientServer(Socket socket, ApplProtocol protocol) throws IOException {
		this.socket = socket;
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
		this.protocol = protocol;
	}

	@Override
	public void run() {
		try {
			while(true) {
				Request request = (Request) input.readObject();
				Response response = protocol.getResponse(request);
				output.writeObject(response);
			}
		} catch(EOFException e) {
			System.out.println("client closed normally connection");
		} catch(Exception e) {
			System.out.println("client closed abnormally connection "
		+ e.getMessage());
		}

	}

}

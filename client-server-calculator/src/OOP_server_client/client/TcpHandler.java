package OOP_server_client.client;

import java.io.*;
import java.net.*;

import OOP_server_client.response_request.Request;
import OOP_server_client.response_request.Response;
import OOP_server_client.response_request.ResponseCode;
public class TcpHandler implements Closeable{
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    public TcpHandler(String host, int port) throws Exception {
    	socket = new Socket(host, port);
    	output = new ObjectOutputStream(socket.getOutputStream());
    	input = new ObjectInputStream(socket.getInputStream());
    }
	@Override
	public void close() throws IOException {
		socket.close();
		
	}
	public <T> T send(String requestType, Serializable requestData)  {
		Request request = new Request(requestType, requestData);
		try {
			output.writeObject(request);
			Response response = (Response) input.readObject();
			if (response.code() != ResponseCode.OK) {
				throw new RuntimeException(response.responseData().toString());
			}
			@SuppressWarnings("unchecked")
			T res = (T) response.responseData();
			return res;
		} catch (Exception e) {
			
			throw new RuntimeException(e.getMessage());
		}
	}

}

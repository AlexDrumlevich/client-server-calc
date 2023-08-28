package OOP_server_client.impl;

import java.util.Arrays;
import OOP_server_client.response_request.Request;
import OOP_server_client.response_request.Response;
import OOP_server_client.response_request.ResponseCode;
import OOP_server_client.server.ApplProtocol;

public class CalculatorProtocol implements ApplProtocol {

	String response = "";
	String requestType;
	Double[] tokens; 
	ResponseCode responseCode = ResponseCode.OK; 
	
	private enum SupportedOperations {
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
	
	@Override
	public Response getResponse(Request request) {
		
		requestType = request.requestType();
		tokens = (Double[]) request.requestData(); 
		
		if(requestType.equals("help")) {
			response = "Request structure:  Supported request types: " + Arrays.toString(SupportedOperations.values()) + ". Data: Double array includes two values";
		} else {

			if(tokens.length == 2) {
				SupportedOperations operation = null;
				try {
					operation = SupportedOperations.valueOf(requestType);
				} catch (Exception e) {
					responseCode = ResponseCode.WRONG_TYPE;
					response = "Wrong operation type, supported operation: " + Arrays.toString(SupportedOperations.values());
				}

				if(operation != null) {
					response = operation.getResult(tokens[0], tokens[1]);
				}
			} else {
				responseCode = ResponseCode.WRONG_DATA;
				response = "Wrong request data, usage: Double array includes two values";
			}
		}
		return new Response(responseCode, response);
	}

}

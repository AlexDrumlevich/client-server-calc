package OOP_server_client.server;

import OOP_server_client.response_request.Request;
import OOP_server_client.response_request.Response;

public interface ApplProtocol {
Response getResponse(Request request);
}

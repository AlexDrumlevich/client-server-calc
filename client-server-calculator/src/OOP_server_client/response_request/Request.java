package OOP_server_client.response_request;

import java.io.Serializable;

public record Request(String requestType, Serializable requestData) 
implements Serializable {

}

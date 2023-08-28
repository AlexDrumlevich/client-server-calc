package OOP_server_client.response_request;

import java.io.Serializable;

public record Response(ResponseCode code, Serializable responseData) implements Serializable
{

}

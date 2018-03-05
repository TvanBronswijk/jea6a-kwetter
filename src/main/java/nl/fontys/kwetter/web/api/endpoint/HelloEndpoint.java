package nl.fontys.kwetter.web.api.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("helloworld")
public class HelloEndpoint {
    @GET
    public String ping(){
        return "Hello World!";
    }
}

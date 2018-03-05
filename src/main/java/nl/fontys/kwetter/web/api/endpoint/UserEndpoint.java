package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.user.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collection;

@Path("users")
public class UserEndpoint {
    @GET
    public Collection<User> get() {
        return null;
    }
}

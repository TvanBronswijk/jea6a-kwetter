package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("")
@Stateless
public class UserEndpoint {

    @Inject
    private UserService userService;

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> get() {
        return userService.readAllUsers();
    }

    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSingle(@PathParam("id") Long id) {
        return userService.readUser(id);
    }
}

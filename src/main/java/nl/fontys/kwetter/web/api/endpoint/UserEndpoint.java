package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.model.user.UserDetails;
import nl.fontys.kwetter.service.da.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Stateless
public class UserEndpoint {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.readAllUsers();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSingleUser(@PathParam("id") Long id) {
        return userService.readUser(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        userService.updateUser(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User deleteUser = new User();
        deleteUser.setId(id);
        userService.deleteUser(deleteUser);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}/follow")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Long id, User follower) {
        User user = userService.readUser(id);
        user.follow(follower);
        userService.updateUser(user);
        return Response.ok().build();
    }

    @GET
    @Path("{id}/details")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDetails getSingleUserDetails(@PathParam("id") Long id) {
        return userService.readUser(id).getUserDetails();
    }


}

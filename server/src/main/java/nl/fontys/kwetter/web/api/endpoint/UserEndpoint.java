package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.annotations.JwtNeeded;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.model.user.UserDetails;
import nl.fontys.kwetter.service.da.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("users")
@Stateless
public class UserEndpoint extends BaseEndpoint {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Collection<User> content = userService.getAll();
        return ok(content);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        User content = userService.get(id);
        return ok(content);
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("name") String name) {
        User content = userService.get(name);
        return ok(content);
    }

    @POST
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        userService.create(user);
        return ok();
    }

    @PUT
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(User user) {
        userService.update(user);
        return ok();
    }

    @DELETE
    @JwtNeeded
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        User deleteUser = new User();
        deleteUser.setId(id);
        userService.delete(deleteUser);
        return ok();
    }

    @PUT
    @JwtNeeded
    @Path("{id}/follow")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response follow(@PathParam("id") Long id, User follower) {
        User user = userService.get(id);
        user.follow(follower);
        userService.update(user);
        return ok();
    }

    @GET
    @Path("{id}/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails(@PathParam("id") Long id) {
        UserDetails content = userService.get(id).getUserDetails();
        return ok(content);
    }


}

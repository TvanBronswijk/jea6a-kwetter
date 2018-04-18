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
public class UserEndpoint {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        Collection<User> content = userService.getAll();
        return Response.ok()
                .entity(content)
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleUser(@PathParam("id") Long id) {
        User content = userService.get(id);
        return Response.ok()
                .entity(content)
                .build();
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleUser(@PathParam("name") String name) {
        User content = userService.get(name);
        return Response.ok()
                .entity(content)
                .build();
    }

    @POST
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        userService.create(user);
        return Response.ok()
                .build();
    }

    @PUT
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        userService.update(user);
        return Response.ok()
                .build();
    }

    @DELETE
    @JwtNeeded
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User deleteUser = new User();
        deleteUser.setId(id);
        userService.delete(deleteUser);
        return Response.ok()
                .build();
    }

    @PUT
    @JwtNeeded
    @Path("{id}/follow")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response followUser(@PathParam("id") Long id, User follower) {
        User user = userService.get(id);
        user.follow(follower);
        userService.update(user);
        return Response.ok()
                .build();
    }

    @GET
    @Path("{id}/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleUserDetails(@PathParam("id") Long id) {
        UserDetails content = userService.get(id).getUserDetails();
        return Response.ok()
                .entity(content)
                .build();
    }


}

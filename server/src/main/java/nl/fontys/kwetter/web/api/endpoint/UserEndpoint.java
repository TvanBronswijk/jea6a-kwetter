package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.annotations.JwtNeeded;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Stateless
public class UserEndpoint {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.ok().entity(userService.readAllUsers()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleUser(@PathParam("id") Long id) {
        return Response.ok().entity(userService.readUser(id)).build();
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleUser(@PathParam("name") String name) {
        return Response.ok().entity(userService.readUser(name)).build();
    }

    @POST
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.ok().build();
    }

    @PUT
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        userService.updateUser(user);
        return Response.ok().build();
    }

    @DELETE
    @JwtNeeded
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User deleteUser = new User();
        deleteUser.setId(id);
        userService.deleteUser(deleteUser);
        return Response.ok().build();
    }

    @PUT
    @JwtNeeded
    @Path("{id}/follow")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response followUser(@PathParam("id") Long id, User follower) {
        User user = userService.readUser(id);
        user.follow(follower);
        userService.updateUser(user);
        return Response.ok().build();
    }

    @GET
    @Path("{id}/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleUserDetails(@PathParam("id") Long id) {
        return Response.ok().entity(userService.readUser(id).getUserDetails()).build();
    }


}

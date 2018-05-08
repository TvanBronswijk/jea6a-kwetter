package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.annotations.JwtNeeded;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.model.user.UserDetails;
import nl.fontys.kwetter.service.da.PostService;
import nl.fontys.kwetter.service.da.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Path("users")
@Stateless
public class UserEndpoint extends BaseEndpoint {

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

    @GET
    @Path("name/{name}/followers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowers(@PathParam("name") String name) {
        Set<User> content = userService.getFollowers(name);
        return ok(content);
    }

    @GET
    @Path("name/{name}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowing(@PathParam("name") String name) {
        Set<User> content = userService.getFollowing(name);
        return ok(content);
    }

    @GET
    @Path("name/{name}/posts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPosts(@PathParam("name") String name) {
        User user = userService.get(name);
        List<Post> content = postService.getByUser(user.getId());
        return ok(content);
    }

    @POST
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        User result = userService.create(user);
        URI location = uriInfo.getBaseUriBuilder().path(String.format("%s", result.getId())).build();
        return created(location);
    }

    @PATCH
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader, User user) {
        User u = user(authHeader);
        u.setUserDetails(user.getUserDetails());
        userService.update(u);
        return ok(u);
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

    @PATCH
    @JwtNeeded
    @Path("{id}/follow")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response follow(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader, @PathParam("id") Long id) {
        User user = userService.get(id);
        user.follow(user(authHeader));
        userService.update(user);
        return ok(user);
    }

    @GET
    @Path("{id}/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails(@PathParam("id") Long id) {
        UserDetails content = userService.get(id).getUserDetails();
        return ok(content);
    }


}

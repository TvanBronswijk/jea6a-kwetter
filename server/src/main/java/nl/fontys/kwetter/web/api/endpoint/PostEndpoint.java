package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.annotations.JwtNeeded;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.PostService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Path("posts")
@Stateless
public class PostEndpoint extends BaseEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Collection<Post> content = postService.getAll();
        return ok(content);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        Post content = postService.get(id);
        return ok(content);
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchTweets(@QueryParam("query") String query) {
        Collection<Post> content = postService.search(query);
        return ok(content);
    }

    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFromUser(@PathParam("id") Long id) {
        Collection<Post> content = postService.getByUser(id);
        return ok(content);
    }

    @POST
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader, Post post) {
        post.setUser(user(authHeader));
        post.setLikes(new HashSet<>());
        Post result = postService.create(post);
        URI location = uriInfo.getBaseUriBuilder().path(String.format("%s", result.getId())).build();
        return created(location, result);
    }

    @PATCH
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader, Post post) {
        if(postService.get(post.getId()).getUser() == user(authHeader)) {
            postService.update(post);
            return ok();
        }
        return unauthorized();
    }

    @DELETE
    @JwtNeeded
    @Path("{id}")
    public Response delete(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader, @PathParam("id") Long id) {
        Post post = postService.get(id);
        if(post.getUser() == user(authHeader)) {
            postService.delete(post);
            return ok();
        }
        return unauthorized();
    }

    @PATCH
    @JwtNeeded
    @Path("{id}/like")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response like(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader, @PathParam("id") Long id) {
        Post post = postService.get(id);
        post.like(user(authHeader));
        postService.update(post);
        return ok(post);
    }
}

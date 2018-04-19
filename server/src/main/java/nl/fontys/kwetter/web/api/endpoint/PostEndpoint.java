package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.annotations.JwtNeeded;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.PostService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

@Path("posts")
@Stateless
public class PostEndpoint extends BaseEndpoint {

    @Context
    private UriInfo uriInfo;
    @Inject
    private PostService postService;

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
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFromUser(@PathParam("id") Long id) {
        Collection<Post> content = postService.getByUser(id);
        return ok(content);
    }

    @POST
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Post post) {
        Post result = postService.create(post);
        URI location = uriInfo.getBaseUriBuilder().path(String.format("%s", result.getId())).build();
        return Response.created(location).build();
    }

    @PUT
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Post post) {
        postService.update(post);
        return ok();
    }

    @DELETE
    @JwtNeeded
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Post deletePost = new Post();
        deletePost.setId(id);
        postService.delete(deletePost);
        return ok();
    }

    @PUT
    @JwtNeeded
    @Path("{id}/like")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response like(@PathParam("id") Long id, User user) {
        Post post = postService.get(id);
        post.like(user);
        postService.update(post);
        return ok();
    }
}

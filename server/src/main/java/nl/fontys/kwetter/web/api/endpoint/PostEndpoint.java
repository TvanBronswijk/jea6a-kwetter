package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.annotations.JwtNeeded;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.PostService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("posts")
@Stateless
public class PostEndpoint {

    @Inject
    private PostService postService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {
        return Response.ok().entity(postService.readAllPosts()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSinglePost(@PathParam("id") Long id) {
        return Response.ok().entity(postService.readPost(id)).build();
    }

    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostFromUser(@PathParam("id") Long id) {
        return Response.ok().entity(postService.readPostsByUser(id)).build();
    }

    @POST
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPost(Post post) {
        postService.createPost(post);
        return Response.ok().build();
    }

    @PUT
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePost(Post post) {
        postService.updatePost(post);
        return Response.ok().build();
    }

    @DELETE
    @JwtNeeded
    @Path("{id}")
    public Response deletePost(@PathParam("id") Long id) {
        Post deletePost = new Post();
        deletePost.setId(id);
        postService.deletePost(deletePost);
        return Response.ok().build();
    }

    @PUT
    @JwtNeeded
    @Path("{id}/like")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response likePost(@PathParam("id") Long id, User user) {
        Post post = postService.readPost(id);
        post.like(user);
        postService.updatePost(post);
        return Response.ok().build();
    }
}

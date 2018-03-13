package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.PostService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("post")
@Stateless
public class PostEndpoint {

    @Inject
    private PostService postService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getAllPosts() {
        return postService.readAllPosts();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getSinglePost(@PathParam("id") Long id) {
        return postService.readPost(id);
    }

    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getPostFromUser(@PathParam("id") Long id) {
        return postService.readPostsByUser(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPost(Post post) {
        postService.createPost(post);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePost(Post post) {
        postService.updatePost(post);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePost(@PathParam("id") Long id) {
        Post deletePost = new Post();
        deletePost.setId(id);
        postService.deletePost(deletePost);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}/like")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response likePost(@PathParam("id") Long id, User user) {
        Post post = postService.readPost(id);
        post.like(user);
        postService.updatePost(post);
        return Response.ok().build();
    }
}

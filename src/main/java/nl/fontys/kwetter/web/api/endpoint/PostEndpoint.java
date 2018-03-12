package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.service.da.PostService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
}

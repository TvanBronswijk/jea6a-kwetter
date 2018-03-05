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
import java.util.Collection;

@Path("")
@Stateless
public class PostEndpoint {

    @Inject
    private PostService postService;

    @GET
    @Path("posts")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Post> get() {
        return postService.readAllPosts();
    }

    @GET
    @Path("post/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getSingle(@PathParam("id") Long id) {
        return postService.readPost(id);
    }
}

package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.post.Post;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collection;

@Path("posts")
public class PostEndpoint {
    @GET
    public Collection<Post> get() {
        return null;
    }
}

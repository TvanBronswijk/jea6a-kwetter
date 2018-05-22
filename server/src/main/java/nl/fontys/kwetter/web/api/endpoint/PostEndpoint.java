package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.annotations.JwtNeeded;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.PostService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import java.net.URI;
import java.util.*;

@Path("posts")
@Stateless
public class PostEndpoint extends BaseEndpoint {

    @Context
    private Sse sse;
    private volatile SseBroadcaster sseBroadcaster;

    @PostConstruct
    public void init(){
        this.sseBroadcaster = sse.newBroadcaster();
    }

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

        Map<String, URI> locations = new HashMap<>();
        locations.put("like", uriInfo.getBaseUriBuilder()
                .path(PostEndpoint.class)
                .path(PostEndpoint.class, "like")
                .build(content.getId()));
        locations.put("delete", uriInfo.getBaseUriBuilder()
                .path(PostEndpoint.class)
                .path(PostEndpoint.class, "delete")
                .build(content.getId()));

        return ok(content, locations);
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
        ((Runnable) () -> sseBroadcaster.broadcast(sse.newEvent("refresh"))).run();
        URI location = uriInfo.getBaseUriBuilder().path(String.format("%s", result.getId())).build();

        Map<String, URI> locations = new HashMap<>();
        locations.put("like", uriInfo.getBaseUriBuilder()
                .path(PostEndpoint.class)
                .path(PostEndpoint.class, "like")
                .build(result.getId()));
        locations.put("delete", uriInfo.getBaseUriBuilder()
                .path(PostEndpoint.class)
                .path(PostEndpoint.class, "delete")
                .build(result.getId()));
        return created(location, result, locations);
    }

    @PATCH
    @JwtNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader, Post post) {
        if(postService.get(post.getId()).getUser() == user(authHeader)) {
            postService.update(post);

            Map<String, URI> locations = new HashMap<>();
            locations.put("like", uriInfo.getBaseUriBuilder()
                    .path(PostEndpoint.class)
                    .path(PostEndpoint.class, "like")
                    .build(post.getId()));
            locations.put("delete", uriInfo.getBaseUriBuilder()
                    .path(PostEndpoint.class)
                    .path(PostEndpoint.class, "delete")
                    .build(post.getId()));

            return ok(post, locations);
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

    @GET
    @Path("subscribe")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void subscribe(@Context SseEventSink eventSink, @Context Sse sse) {
        sseBroadcaster.register(eventSink);
    }
}

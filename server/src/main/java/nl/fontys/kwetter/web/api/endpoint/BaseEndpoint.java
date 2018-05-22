package nl.fontys.kwetter.web.api.endpoint;

import com.auth0.jwt.interfaces.DecodedJWT;
import nl.fontys.kwetter.model.auth.Token;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.auth.JsonWebTokenService;
import nl.fontys.kwetter.service.da.AuthenticationService;
import nl.fontys.kwetter.service.da.PostService;
import nl.fontys.kwetter.service.da.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;

import static javax.ws.rs.core.Response.Status.*;

@Stateless
public abstract class BaseEndpoint {

    @Context
    protected UriInfo uriInfo;
    @Context
    protected SecurityContext securityContext;

    @Inject
    protected AuthenticationService authenticationService;
    @Inject
    protected UserService userService;
    @Inject
    protected PostService postService;

    /**
     * Retrieve the user from the JWT.
     * @return the {@link User} object.
     */
    protected User user(String authHeader) {
        String token = authHeader.substring("Bearer".length()).trim();
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JsonWebTokenService.parseToken(new Token(token));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (decodedJWT != null) {
            return userService.get(decodedJWT.getClaim("username").asString());
        }
        return null;
    }


    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 202 - ACCEPTED
     * @return 200 - OK
     */
    protected Response accepted() {
        return Response.accepted()
                .build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 202 - ACCEPTED and a body
     * @return 200 - OK
     */
    protected <T> Response accepted(T entity) {
        return Response.accepted()
                .entity(entity)
                .build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 400 - BAD REQUEST
     * @return 200 - OK
     */
    protected Response badRequest() {
        return status(BAD_REQUEST);
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 400 - BAD REQUEST and a body
     * @return 200 - OK
     */
    protected <T> Response badRequest(T entity) {
        return status(BAD_REQUEST, entity);
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 201 - CREATED
     * @return 200 - OK
     */
    protected Response created(URI location) {
        return Response.created(location)
                .build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 201 - CREATED and a body
     * @return 200 - OK
     */
    protected <T> Response created(URI location, T entity) {
        return Response.created(location)
                .entity(entity)
                .build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 201 - CREATED and a body
     * @return 200 - OK
     */
    protected <T> Response created(URI location, T entity, Map<String, URI> locations) {
        Response.ResponseBuilder builder = Response.created(location)
                .entity(entity);

        for(Map.Entry<String, URI> pair : locations.entrySet()){
            builder.link(pair.getValue(), pair.getKey());
        }

        return builder.build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 403 - FORBIDDEN
     * @return 401 - UNAUTHORIZED
     */
    protected Response forbidden(){
        return status(FORBIDDEN);
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 403 - FORBIDDEN and a body
     * @return 401 - UNAUTHORIZED
     */
    protected <T> Response forbidden(T entity){
        return status(FORBIDDEN, entity);
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 204 - NO CONTENT
     * @return 200 - OK
     */
    protected Response noContent() {
        return Response.noContent()
                .build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 204 - NO CONTENT and a body
     * @return 200 - OK
     */
    protected <T> Response noContent(T entity) {
        return Response.noContent()
                .entity(entity)
                .build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 404 - NOT FOUND
     * @return 200 - OK
     */
    protected Response notFound() {
        return status(NOT_FOUND);
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 404 - NOT FOUND and a body
     * @return 200 - OK
     */
    protected <T> Response notFound(T entity) {
        return status(NOT_FOUND, entity);
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 200 - OK
     * @return 200 - OK
     */
    protected Response ok() {
        return Response.ok()
                .build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 200 - OK and a body
     * @return 200 - OK
     */
    protected <T> Response ok(T entity) {
        return Response.ok()
                .entity(entity)
                .build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 200 - OK and a body
     * @return 200 - OK
     */
    protected <T> Response ok(T entity, Map<String, URI> locations) {
        Response.ResponseBuilder builder = Response.ok()
                .entity(entity);

        for(Map.Entry<String, URI> pair : locations.entrySet()){
            builder.link(pair.getValue(), pair.getKey());
        }

        return builder.build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 303 - SEE OTHER
     * @return 200 - OK
     */
    protected Response redirect(URI location) {
        return Response.seeOther(location)
                .build();
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 401 - UNAUTHORIZED
     * @return 401 - UNAUTHORIZED
     */
    protected Response unauthorized(){
        return status(UNAUTHORIZED);
    }

    /**
     * Creates a {@link javax.ws.rs.core.Response} object with status 401 - UNAUTHORIZED and a body
     * @return 401 - UNAUTHORIZED
     */
    protected <T> Response unauthorized(T entity){
        return status(UNAUTHORIZED, entity);
    }

    protected Response status(Response.Status statusCode){
        return Response.status(statusCode)
                .build();
    }

    protected <T> Response status(Response.Status statusCode, T entity){
        return Response.status(statusCode)
                .entity(entity)
                .build();
    }
}

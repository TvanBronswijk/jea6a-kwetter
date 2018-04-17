package nl.fontys.kwetter.annotations.handler;

import nl.fontys.kwetter.annotations.JwtNeeded;
import nl.fontys.kwetter.model.auth.Token;
import nl.fontys.kwetter.service.auth.JsonWebTokenService;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@JwtNeeded
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthorizationHandler implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        try {
            String token = authorizationHeader.substring("Bearer".length()).trim();
            JsonWebTokenService.verifyToken(new Token(token));
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}

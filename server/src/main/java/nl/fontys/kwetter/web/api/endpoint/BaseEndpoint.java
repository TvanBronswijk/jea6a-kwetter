package nl.fontys.kwetter.web.api.endpoint;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Stateless
public abstract class BaseEndpoint {

    /**
     * A response with an OK HTTP response
     * @return 200 - OK
     */
    protected Response ok() {
        return Response.ok()
                .build();
    }

    /**
     * A response with an OK HTTP response and an object in it's body
     * @param entity The body of the Response
     * @return 200 - OK
     */
    protected Response ok(Object entity) {
        return Response.ok()
                .entity(entity)
                .build();
    }

    /**
     * A response with an UNAUTHORIZED HTTP response
     * @return 401 - UNAUTHORIZED
     */
    protected Response unauthorized(){
        return Response.status(UNAUTHORIZED)
                .build();
    }
}

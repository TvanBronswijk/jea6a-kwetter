package nl.fontys.kwetter.web.api.endpoint;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Stateless
public abstract class BaseEndpoint {

    protected Response ok() {
        return Response.ok()
                .build();
    }

    protected Response ok(Object entity) {
        return Response.ok()
                .entity(entity)
                .build();
    }

    protected Response unauthorized(){
        return Response.status(UNAUTHORIZED)
                .build();
    }
}

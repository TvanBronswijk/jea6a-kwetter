package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.auth.Login;
import nl.fontys.kwetter.model.auth.Register;
import nl.fontys.kwetter.model.auth.Token;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.auth.JsonWebTokenService;
import nl.fontys.kwetter.service.da.AuthenticationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
@Stateless
public class AuthenticationEndpoint extends BaseEndpoint {

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Register register) {
        try {
            User result = authenticationService.register(register);
            return ok(result);
        } catch (Exception e) {
            return unauthorized();
        }
    }

    @POST
    @Path("token/request")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response request(Login login) {
        try {
            User user = authenticationService.validate(login);
            Token token = JsonWebTokenService.generateToken(user);
            return ok(token);
        } catch (Exception e) {
            return unauthorized();
        }
    }
}

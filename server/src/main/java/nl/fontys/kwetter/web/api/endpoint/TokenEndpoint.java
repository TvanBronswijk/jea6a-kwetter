package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.exceptions.InvalidPasswordException;
import nl.fontys.kwetter.model.auth.Login;
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
import java.io.UnsupportedEncodingException;

@Path("tokens")
@Stateless
public class TokenEndpoint extends BaseEndpoint {

    @Inject
    private AuthenticationService authenticationService;

    @POST
    @Path("request")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        try {
            User user = authenticationService.validate(login);
            Token token = JsonWebTokenService.generateToken(user);
            return ok(token);
        } catch (InvalidPasswordException | UnsupportedEncodingException e) {
            return unauthorized();
        }
    }
}

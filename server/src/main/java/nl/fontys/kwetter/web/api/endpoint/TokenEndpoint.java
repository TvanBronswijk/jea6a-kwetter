package nl.fontys.kwetter.web.api.endpoint;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import nl.fontys.kwetter.model.auth.Login;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.auth.JsonWebTokenService;
import nl.fontys.kwetter.service.da.UserService;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.UnsupportedEncodingException;

@Path("tokens")
@Stateless
public class TokenEndpoint {

    @Inject
    private UserService userService;

    @POST
    @Path("request")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        User dbUser = userService.readUser(login.getUsername());
        if (userService.validatePassword(dbUser, login.getPassword())) {
            try {
                return Response.ok(JsonWebTokenService.generateToken(dbUser)).build();
            } catch (UnsupportedEncodingException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

package nl.fontys.kwetter.service.auth;

import nl.fontys.kwetter.da.inf.user.UserDa;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginManager {

    @Inject
    private UserDa users;

    public boolean login(String username, String password) {
        return false;
    }

    public void logout() {

    }
}

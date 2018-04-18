package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.exceptions.InvalidPasswordException;
import nl.fontys.kwetter.exceptions.NoSuchRoleException;
import nl.fontys.kwetter.model.auth.Login;
import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.helper.AuthenticationUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Transactional
@Stateless
public class AuthenticationService {

    @Inject
    private UserDa users;
    @Inject
    private RoleDa roles;

    public void createRole(Role role) {
        roles.create(role);
    }

    public Role getRole(String name) throws NoSuchRoleException {
        Role result = roles.read(name);
        if (result == null) {
            throw new NoSuchRoleException();
        }
        return result;
    }

    public User validate(Login login) throws InvalidPasswordException {
        User user = users.read(login.getUsername());
        if(user.getPassword().equals(login.getEncodedPassword())){
           return user;
        }
        throw new InvalidPasswordException();
    }
}

package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.user.RoleDa;
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
    private RoleDa roles;

    public void createRole(Role role) {
        roles.create(role);
    }

    public Role getRole(String name) throws Exception {
        Role result = roles.read(name);
        if (result == null) {
            throw new Exception();
        }
        return result;
    }

    public boolean validatePassword(User user, String password) {
        try {
            return user.getPassword().equals(AuthenticationUtil.encodeSHA256(password));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}

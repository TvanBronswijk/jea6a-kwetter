package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.exceptions.InvalidPasswordException;
import nl.fontys.kwetter.exceptions.NoSuchRoleException;
import nl.fontys.kwetter.model.auth.Login;
import nl.fontys.kwetter.model.auth.Register;
import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.model.user.UserDetails;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Collections;

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

    public User register(Register register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(register.getEncodedPassword());
        user.setEmail(register.getEmail());
        try {
            user.setRoles(Collections.singleton(getRole(Role.USER)));
        } catch (NoSuchRoleException e) {
            e.printStackTrace();
        }
        user.setUserDetails(new UserDetails());
        users.create(user);
        return user;
    }

    public User validate(Login login) throws InvalidPasswordException, NoResultException {
        User user = users.read(login.getUsername());
        if (user.getPassword().equals(login.getEncodedPassword())) {
            return user;
        }
        throw new InvalidPasswordException();

    }
}

package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.Crud;
import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.da.inf.user.UserDetailsDa;
import nl.fontys.kwetter.da.jpa.DataAccessBase;
import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.model.user.UserDetails;
import nl.fontys.kwetter.service.helper.AuthenticationUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Transactional
@Stateless
public class UserService extends CrudService<User> {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Inject
    private UserDa users;


    @Override
    protected Crud<User> getDao() {
        return users;
    }

    @Override
    public User create(User user) {
        try {
            user.setPassword(AuthenticationUtil.encodeSHA256(user.getPassword()));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        users.create(user);
        return user;
    }

    public User get(String username) {
        return users.read(username);
    }

    public List<User> getAll() {
        return users.readAll();
    }

    public Set<User> getFollowers(String username) {
        return new HashSet<User>(users.readFollowersFromUser(username));
    }

    public Set<User> getFollowing(String username) {
        return new HashSet<User>(users.readFollowingFromUser(username));
    }
}
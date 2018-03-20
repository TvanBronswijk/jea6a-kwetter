package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.da.inf.user.UserDetailsDa;
import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.model.user.UserDetails;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Stateless
public class UserService {

    @Inject
    private UserDa users;
    @Inject
    private RoleDa roles;
    @Inject
    private UserDetailsDa userDetails;


    public void createUser(User user) {
        users.create(user);
    }

    public User readUser(Long id) {
        return users.read(id);
    }

    public List<User> readAllUsers() {
        return users.readAll();
    }

    public void updateUser(User user) {
        users.update(user);
    }

    public void deleteUser(User user) {
        users.delete(user);
    }

    public void createUserDetails(UserDetails userDetails) {
        this.userDetails.create(userDetails);
    }

    public UserDetails readUserDetails(Long id) {
        return userDetails.read(id);
    }

    public void updateUserDetails(UserDetails userDetails) {
        this.userDetails.update(userDetails);
    }

    public void deleteUserDetails(UserDetails userDetails) {
        this.userDetails.delete(userDetails);
    }

    public void createRole(Role role) {
        roles.create(role);
    }

    public Role readRole(Long id) {
        return roles.read(id);
    }

    public void updateRole(Role role) {
        roles.update(role);
    }

    public void deleteRole(Role role) {
        roles.delete(role);
    }
}
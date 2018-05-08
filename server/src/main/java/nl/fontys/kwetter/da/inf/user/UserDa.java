package nl.fontys.kwetter.da.inf.user;

import nl.fontys.kwetter.da.inf.Crud;
import nl.fontys.kwetter.model.user.User;

import java.util.List;
import java.util.Set;

public interface UserDa extends Crud<User> {
    User read(String username);
    List<User> readAll();
    List<User> readFollowersFromUser(String username);
    List<User> readFollowingFromUser(String username);
}

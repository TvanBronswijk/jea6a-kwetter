package nl.fontys.kwetter.da.inf.user;

import nl.fontys.kwetter.da.inf.Crud;
import nl.fontys.kwetter.model.user.User;

import java.util.List;

public interface UserDa extends Crud<User> {
    List<User> readAll();
}

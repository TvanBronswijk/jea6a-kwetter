package nl.fontys.kwetter.da.inf.user;

import nl.fontys.kwetter.da.inf.Crud;
import nl.fontys.kwetter.model.user.User;

import java.util.Collection;

public interface UserDa extends Crud<User> {
    Collection<User> readAll();
}

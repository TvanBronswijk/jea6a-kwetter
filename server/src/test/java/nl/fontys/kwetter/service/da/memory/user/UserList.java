package nl.fontys.kwetter.service.da.memory.user;

import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.memory.TestDataAccessBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserList extends TestDataAccessBase<User> implements UserDa {
    @Override
    public List<User> readAll() {
        List<User> result = new ArrayList<>(data);
        result.removeAll(Collections.singleton(null));
        return result;
    }
}

package nl.fontys.kwetter.da.jpa.user;

import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.da.jpa.DataAccessBase;
import nl.fontys.kwetter.model.user.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDao extends DataAccessBase<User> implements UserDa {
    public UserDao() {
        super(User.class);
    }

    @Override
    public List readAll() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }
}

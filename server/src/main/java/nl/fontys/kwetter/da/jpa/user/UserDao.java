package nl.fontys.kwetter.da.jpa.user;

import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.da.jpa.DataAccessBase;
import nl.fontys.kwetter.model.user.User;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class UserDao extends DataAccessBase<User> implements UserDa {
    public UserDao() {
        super(User.class);
    }

    @Override
    public User read(String username) throws NoResultException {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public List<User> readAll() throws NoResultException {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public List<User> readFollowersFromUser(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return new ArrayList<User>(query.getSingleResult().getFollowers());
    }

    @Override
    public List<User> readFollowingFromUser(String username) {
        User user = read(username);
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE :user MEMBER OF u.followers", User.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
}

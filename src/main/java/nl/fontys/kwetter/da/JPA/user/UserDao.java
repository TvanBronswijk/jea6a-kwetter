package nl.fontys.kwetter.da.JPA.user;

import nl.fontys.kwetter.da.JPA.DataAccessBase;
import nl.fontys.kwetter.da.JPA.JPA;
import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.model.user.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Stateless
@JPA
public class UserDao extends DataAccessBase<User> implements UserDa {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDao() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Collection<User> readAll() {
        Query query = getEntityManager().createQuery("SELECT u FROM User u");
        return query.getResultList();
    }
}

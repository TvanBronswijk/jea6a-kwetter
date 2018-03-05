package nl.fontys.kwetter.da.JPA.user;

import nl.fontys.kwetter.da.JPA.DataAccessBase;
import nl.fontys.kwetter.da.JPA.JPA;
import nl.fontys.kwetter.da.inf.user.UserDetailsDa;
import nl.fontys.kwetter.model.user.UserDetails;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@JPA
public class UserDetailsDao extends DataAccessBase<UserDetails> implements UserDetailsDa {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDetailsDao() {
        super(UserDetails.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

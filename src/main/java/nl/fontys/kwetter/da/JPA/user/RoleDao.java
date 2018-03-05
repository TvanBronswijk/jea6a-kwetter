package nl.fontys.kwetter.da.JPA.user;

import nl.fontys.kwetter.da.JPA.DataAccessBase;
import nl.fontys.kwetter.da.JPA.JPA;
import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.model.user.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@JPA
public class RoleDao extends DataAccessBase<Role> implements RoleDa {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDao() {
        super(Role.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

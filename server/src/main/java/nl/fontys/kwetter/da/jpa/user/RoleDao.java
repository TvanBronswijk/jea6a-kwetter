package nl.fontys.kwetter.da.jpa.user;

import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.da.jpa.DataAccessBase;
import nl.fontys.kwetter.model.user.Role;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class RoleDao extends DataAccessBase<Role> implements RoleDa {
    public RoleDao() {
        super(Role.class);
    }

    @Override
    public Role read(String name) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT u FROM Role u WHERE u.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

}

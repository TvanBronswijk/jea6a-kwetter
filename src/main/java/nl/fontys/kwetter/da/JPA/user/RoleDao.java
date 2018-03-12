package nl.fontys.kwetter.da.JPA.user;

import nl.fontys.kwetter.da.JPA.DataAccessBase;
import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.model.user.Role;

import javax.ejb.Stateless;

@Stateless
public class RoleDao extends DataAccessBase<Role> implements RoleDa {
    public RoleDao() {
        super(Role.class);
    }

}

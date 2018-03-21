package nl.fontys.kwetter.da.jpa.user;

import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.da.jpa.DataAccessBase;
import nl.fontys.kwetter.model.user.Role;

import javax.ejb.Stateless;

@Stateless
public class RoleDao extends DataAccessBase<Role> implements RoleDa {
    public RoleDao() {
        super(Role.class);
    }

}

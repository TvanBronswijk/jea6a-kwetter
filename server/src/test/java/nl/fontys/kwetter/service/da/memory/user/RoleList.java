package nl.fontys.kwetter.service.da.memory.user;

import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.service.da.memory.TestDataAccessBase;

public class RoleList extends TestDataAccessBase<Role> implements RoleDa {
    @Override
    public Role read(String name) {
        return new Role(name);
    }
}

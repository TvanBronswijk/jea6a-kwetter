package nl.fontys.kwetter.da.jpa.user;

import nl.fontys.kwetter.da.inf.user.UserDetailsDa;
import nl.fontys.kwetter.da.jpa.DataAccessBase;
import nl.fontys.kwetter.model.user.UserDetails;

import javax.ejb.Stateless;

@Stateless
public class UserDetailsDao extends DataAccessBase<UserDetails> implements UserDetailsDa {
    public UserDetailsDao() {
        super(UserDetails.class);
    }
}

package nl.fontys.kwetter.da.jpa.view;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RealmViewService {

    @PersistenceContext
    protected EntityManager entityManager;

    public void createView() {
        Query q = entityManager.createNativeQuery("" +
                "CREATE OR ALTER VIEW v_realm AS \n" +
                "SELECT UserEntity_Role.User_id, UserEntity_Role.roles_id, UserEntity.username, UserEntity.password, Role.name \n" +
                "FROM UserEntity_Role \n" +
                "INNER JOIN UserEntity ON UserEntity_Role.User_id = UserEntity.id \n" +
                "INNER JOIN Role ON UserEntity_Role.roles_id = Role.id \n");
        q.executeUpdate();
    }
}

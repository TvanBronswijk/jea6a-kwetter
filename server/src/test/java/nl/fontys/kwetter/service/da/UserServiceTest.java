package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.user.RoleDa;
import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.da.inf.user.UserDetailsDa;
import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.model.user.UserDetails;
import nl.fontys.kwetter.service.da.memory.user.RoleList;
import nl.fontys.kwetter.service.da.memory.user.UserDetailsList;
import nl.fontys.kwetter.service.da.memory.user.UserList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class UserServiceTest {

    @Spy
    private final UserDa userDa = new UserList();
    @Spy
    private final UserDetailsDa userDetailsDa = new UserDetailsList();
    @Spy
    private final RoleDa roleDa = new RoleList();

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUsers() {
        //Create a User
        User object = new User();
        object.setId(1L);
        object.setPassword("password");
        userService.create(object);

        //Find a User
        assertThat(userService.get(1L).getUsername(), is(object.getUsername()));

        //Find All Users
        assertThat(userService.getAll().size(), is(1));

        //Update User
        object.setUsername("Test User");
        userService.update(object);
        assertThat(userService.get(1L).getUsername(), is(object.getUsername()));

        //Delete User
        userService.delete(object);
        assertThat(userService.getAll().size(), is(0));
    }
}

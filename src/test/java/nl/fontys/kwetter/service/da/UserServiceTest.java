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
        userService.createUser(object);

        //Find a User
        assertThat(userService.readUser(1L), is(object));

        //Find All Users
        assertThat(userService.readAllUsers().size(), is(1));

        //Update User
        object.setUsername("Test User");
        userService.updateUser(object);
        assertThat(userService.readUser(1L), is(object));

        //Delete User
        userService.deleteUser(object);
        assertThat(userService.readAllUsers().size(), is(0));
    }


    @Test
    public void testUserDetails() {
        //Create a UserDetails
        UserDetails object = new UserDetails();
        object.setId(1L);
        userService.createUserDetails(object);

        //Find a UserDetails
        assertThat(userService.readUserDetails(1L), is(object));

        //Update UserDetails
        object.setName("Test UserDetails");
        userService.updateUserDetails(object);
        assertThat(userService.readUserDetails(1L), is(object));

        //Delete UserDetails
        userService.deleteUserDetails(object);
        assertThat(userService.readUserDetails(1L), is(nullValue()));
    }

    @Test
    public void testRoles() {
        //Create a Role
        Role object = new Role();
        object.setId(1L);
        userService.createRole(object);

        //Find a Role
        assertThat(userService.readRole(1L), is(object));

        //Update Role
        object.setName("Test Role");
        userService.updateRole(object);
        assertThat(userService.readRole(1L), is(object));

        //Delete Role
        userService.deleteRole(object);
        assertThat(userService.readRole(1L), is(nullValue()));
    }
}

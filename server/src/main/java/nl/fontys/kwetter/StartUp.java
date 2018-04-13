/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.fontys.kwetter;

import nl.fontys.kwetter.da.jpa.view.RealmViewService;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.model.user.UserDetails;
import nl.fontys.kwetter.service.da.PostService;
import nl.fontys.kwetter.service.da.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

@Singleton
@Startup
public class StartUp {

    @Inject
    private UserService userService;
    @Inject
    private PostService postService;
    @Inject
    private RealmViewService realmViewService;

    @PostConstruct
    private void initialize() {
        //Roles
        Role adminRole = new Role("Administrator");
        userService.createRole(adminRole);
        Role userRole = new Role("User");
        userService.createRole(userRole);

        //Admin User
        User adminUser = new User("admin", "admin", "admin@kwetter.nl", null);
        adminUser.addRole(userRole);
        adminUser.addRole(adminRole);
        userService.createUser(adminUser);

        //First User
        UserDetails firstUserDetails = new UserDetails("Original User",
                "https://t0.rbxcdn.com/178e709dda9fc9503605006f507c7216",
                "The Internet",
                "I am the first user on Kwetter!",
                "https://www.google.nl/");
        User firstUser = new User("first", "hunter2", "first@user.nl", firstUserDetails);
        firstUser.addRole(userRole);
        userService.createUser(firstUser);

        //First Post
        Post post = new Post(firstUser,
                "This is the first post on Kwetter! #first",
                new Date());
        postService.createPost(post);

        realmViewService.createView();
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.kwetter;

import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartUp {
    @Inject
    private UserService userService;

    public StartUp() {
    }

    @PostConstruct
    private void databaseSeed(){
        Role adminRole = new Role("Administrator");
        userService.createRole(adminRole);
        User adminUser = new User("admin", "admin", "admin@kwetter.nl", adminRole, null);
        userService.createUser(adminUser);
    }

}

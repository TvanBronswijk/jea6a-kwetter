package nl.fontys.kwetter.web.faces;

import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.UserService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named("admin")
@ViewScoped
public class AdminController implements Serializable {

    @Inject
    private UserService service;

    public Collection<User> getUsers() {
        return service.readAllUsers();
    }

}
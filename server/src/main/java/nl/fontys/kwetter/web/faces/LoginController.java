package nl.fontys.kwetter.web.faces;

import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

@Named("login")
@ViewScoped
public class LoginController implements Serializable {

    @Inject
    private UserService userService;

    private User user;
    private String username;
    private String password;

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(username, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
            return;
        }
        Principal principal = request.getUserPrincipal();
        this.user = userService.get(username);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("User", user);
        if (user.isAdmin()) {
            context.getExternalContext().redirect("/admin");
        }
    }

    public User getAuthenticatedUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package nl.fontys.kwetter.web.faces;

import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.AuthenticationService;
import nl.fontys.kwetter.service.da.PostService;
import nl.fontys.kwetter.service.da.UserService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.ExcludeClassInterceptors;
import javax.resource.spi.AuthenticationMechanism;
import java.io.Serializable;
import java.util.Collection;

@Named("admin")
@ViewScoped
public class AdminController implements Serializable {

    @Inject
    private AuthenticationService authenticationService;
    @Inject
    private UserService userService;
    @Inject
    private PostService postService;

    public Collection<User> getUsers() {
        return userService.getAll();
    }

    public void elevateUser(User user) throws Exception {
        user.addRole(authenticationService.getRole(Role.ADMIN));
        userService.update(user);
    }

    public void deleteUser(User user) {
        userService.delete(user);
    }

    public Collection<Post> getPosts() {
        return postService.getAll();
    }

    public void deletePost(Post post) {
        postService.delete(post);
    }

}
package nl.fontys.kwetter.web.faces;

import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.PostService;
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
    private UserService userService;
    @Inject
    private PostService postService;

    public Collection<User> getUsers() {
        return userService.readAllUsers();
    }

    public void elevateUser(User user) {
        user.setRole(userService.readRole(1L));
        userService.updateUser(user);
    }

    public void deleteUser(User user) {
        userService.deleteUser(user);
    }

    public Collection<Post> getPosts() {
        return postService.readAllPosts();
    }

    public void deletePost(Post post) {
        postService.deletePost(post);
    }

}
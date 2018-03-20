package nl.fontys.kwetter.model.user;

import nl.fontys.kwetter.model.Model;
import nl.fontys.kwetter.model.post.Post;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "USERENTITY")
@XmlRootElement
@JsonIgnoreProperties( {"followers", "posts"})
public class User implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @ManyToOne
    private Role role;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private UserDetails userDetails;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> followers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.ALL})
    private Collection<Post> posts;

    private String password;
    private String email;

    public User() {
    }

    public User(String username, String password, String email, Role role, UserDetails userDetails) {
        this.username = username;
        this.role = role;
        this.userDetails = userDetails;
        this.password = password;
        this.email = email;
        posts = new ArrayList<>();
        followers = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Collection<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<User> follow) {
        followers = follow;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public void follow(User follower) {
        followers.add(follower);
    }
}

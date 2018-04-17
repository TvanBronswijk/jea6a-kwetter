package nl.fontys.kwetter.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.fontys.kwetter.model.Model;
import nl.fontys.kwetter.model.post.Post;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "UserEntity")
@XmlRootElement
public class User implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private UserDetails userDetails;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<User> followers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private Collection<Post> posts;

    @JsonIgnore
    private String password;
    private String email;

    public User() {
    }

    public User(String username, String password, String email, UserDetails userDetails) {
        this.username = username;
        this.userDetails = userDetails;
        this.password = password;
        this.email = email;
        roles = new ArrayList<>();
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public boolean isAdmin(){
        return roles.stream().anyMatch(role -> Role.ADMIN.equals(role.getName()));
    }
}

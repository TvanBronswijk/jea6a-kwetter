package nl.fontys.kwetter.model.user;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import com.voodoodyne.jackson.jsog.JSOGRefDeserializer;
import nl.fontys.kwetter.model.Model;
import nl.fontys.kwetter.model.post.Post;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "UserEntity")
@XmlRootElement
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToOne(cascade = {CascadeType.ALL})
    private UserDetails userDetails;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> followers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private Set<Post> posts;

    @NotNull
    @JsonIgnore
    private String password;
    @NotNull
    private String email;

    public User() {
    }

    public User(String username, String password, String email, UserDetails userDetails) {
        this.username = username;
        this.userDetails = userDetails;
        this.password = password;
        this.email = email;
        roles = new HashSet<>();
        posts = new HashSet<>();
        followers = new HashSet<>();
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

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> follow) {
        followers = follow;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void follow(User follower) {
        followers.add(follower);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public boolean isAdmin(){
        return roles.stream().anyMatch(role -> Role.ADMIN.equals(role.getName()));
    }
}

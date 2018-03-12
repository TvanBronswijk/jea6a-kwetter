package nl.fontys.kwetter.model.user;

import nl.fontys.kwetter.model.Model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "KWETTERUSERS")
@XmlRootElement
public class User implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    @ManyToOne
    private Role role;
    @OneToOne(cascade = CascadeType.PERSIST)
    private UserDetails userDetails;
    @ManyToMany
    private List<User> follow;

    public User() {
    }

    public User(String username, String password, String email, Role role, UserDetails userDetails) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userDetails = userDetails;
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

    public List<User> getFollow() {
        return follow;
    }

    public void setFollow(List<User> follow) {
        this.follow = follow;
    }
}

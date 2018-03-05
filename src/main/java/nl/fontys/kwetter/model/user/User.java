package nl.fontys.kwetter.model.user;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="KWETTERUSERS")
@XmlRootElement
public class User {

    @Id
    @GeneratedValue
    private Long Id;
    @Column(unique=true)
    private String username;
    private String password;
    private String email;
    @ManyToOne
    private Role role;
    @OneToOne
    private UserDetails userDetails;

    public User() {
    }

    public User(String username, String password, String email, Role role, UserDetails userDetails) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userDetails = userDetails;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}

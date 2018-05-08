package nl.fontys.kwetter.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.fontys.kwetter.model.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageURL;
    private String location;
    private String bio;
    private String web;

    public UserDetails() {
    }

    public UserDetails(String name, String imageURL, String location, String bio, String web) {
        this.name = name;
        this.imageURL = imageURL;
        this.location = location;
        this.bio = bio;
        this.web = web;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}

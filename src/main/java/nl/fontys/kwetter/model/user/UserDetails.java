package nl.fontys.kwetter.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class UserDetails {
    @Id
    @GeneratedValue
    private Long Id;
    private String Name;
    private String ImageURL;
    private String Location;
    private String Bio;
    private String Web;

    public UserDetails() {
    }

    public UserDetails(String name, String imageURL, String location, String bio, String web) {
        Name = name;
        ImageURL = imageURL;
        Location = location;
        Bio = bio;
        Web = web;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getWeb() {
        return Web;
    }

    public void setWeb(String web) {
        Web = web;
    }
}

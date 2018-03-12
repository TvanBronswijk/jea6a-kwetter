package nl.fontys.kwetter.model.post;

import nl.fontys.kwetter.model.Model;
import nl.fontys.kwetter.model.user.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Date;

@Entity
@XmlRootElement
public class Post implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String content;
    private Date timestamp;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Tag> tags;

    public Post() {
    }

    public Post(User user, String content, Date timestamp, Collection<Tag> tags) {
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
        this.tags = tags;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }
}

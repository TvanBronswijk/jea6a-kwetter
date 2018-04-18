package nl.fontys.kwetter.model.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.fontys.kwetter.model.Model;
import nl.fontys.kwetter.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@XmlRootElement
public class Post implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<User> likes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<User> mentions;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Collection<Tag> tags;

    @NotNull
    private String content;
    @NotNull
    private Date timestamp;

    public Post() {
    }

    public Post(User user, String content, Date timestamp) {
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
        tags = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<User> getLikes() {
        return likes;
    }

    public void setLikes(Collection<User> likes) {
        this.likes = likes;
    }

    public void like(User user) {
        likes.add(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<User> getMentions() {
        return mentions;
    }

    public void setMentions(Collection<User> mentions) {
        this.mentions = mentions;
    }
}

package nl.fontys.kwetter.model.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.fontys.kwetter.model.Model;
import nl.fontys.kwetter.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@Entity
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="post_likes")
    @JsonIgnore
    private Set<User> likes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="post_mentions")
    @JsonIgnore
    private Set<User> mentions;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Tag> tags;

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
        tags = new HashSet<>();
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public void like(User user) {
        likes.add(user);
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public int getLikeCount(){
        return likes.size();
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

    public void setMentions(Set<User> mentions) {
        this.mentions = mentions;
    }
}

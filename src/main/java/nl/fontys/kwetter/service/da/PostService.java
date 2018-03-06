package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.JPA.JPA;
import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.da.inf.post.TagDa;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.post.Tag;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

@Stateless
public class PostService {

    @Inject
    @JPA
    private PostDa posts;
    @Inject
    @JPA
    private TagDa tags;

    public void createPost(Post post) {
        posts.create(post);
    }

    public Post readPost(Long id) {
        return posts.read(id);
    }

    public Collection<Post> readAllPosts() {
        return posts.readAll();
    }

    public void updatePost(Post post) {
        posts.update(post);
    }

    public void deletePost(Post post) {
        posts.delete(post);
    }

    public void createTag(Tag tag) {
        tags.create(tag);
    }

    public Tag readTag(Long id) {
        return tags.read(id);
    }

    public void updateTag(Tag tag) {
        tags.update(tag);
    }

    public void deleteTag(Tag tag) {
        tags.delete(tag);
    }
}

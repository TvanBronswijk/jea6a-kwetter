package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.da.inf.post.TagDa;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.post.Tag;
import nl.fontys.kwetter.service.helper.TagParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
public class PostService {

    @Inject
    private PostDa posts;
    @Inject
    private TagDa tags;
    @Inject
    private TagParser tagParser;

    public void createPost(Post post) {
        post.setTimestamp(new Date());
        post.setTags(tagParser.parseString(post.getContent()));
        posts.create(post);
    }

    public Post readPost(Long id) {
        return posts.read(id);
    }

    public List<Post> readAllPosts() {
        return posts.readAll();
    }

    public void updatePost(Post post) {
        posts.update(post);
    }

    public void deletePost(Post post) {
        posts.delete(post);
    }

    public List<Post> readPostsByUser(Long userId) {
        return posts.readByUserId(userId);
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

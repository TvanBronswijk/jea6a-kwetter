package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.Crud;
import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.da.inf.post.TagDa;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.post.Tag;
import nl.fontys.kwetter.service.helper.TagParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Stateless
public class PostService extends CrudService<Post>{

    @Inject
    private PostDa posts;
    @Inject
    private TagParser tagParser;


    @Override
    protected Crud<Post> getDao() {
        return posts;
    }

    @Override
    public Post create(Post post) {
        post.setTimestamp(new Date());
        post.setTags(tagParser.parseString(post.getContent()));
        posts.create(post);
        return post;
    }

    public List<Post> getAll() {
        return posts.readAll();
    }

    public List<Post> getByUser(Long userId) {
        return posts.readByUserId(userId);
    }
}

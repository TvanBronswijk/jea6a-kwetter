package nl.fontys.kwetter.da.JPA.post;

import nl.fontys.kwetter.da.JPA.DataAccessBase;
import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.model.post.Post;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PostDao extends DataAccessBase<Post> implements PostDa {
    public PostDao() {
        super(Post.class);
    }

    @Override
    public List<Post> readAll() {
        Query query = entityManager.createQuery("SELECT p FROM Post p");
        return query.getResultList();
    }

    @Override
    public List<Post> readByUserId(Long userId) {
        Query query = entityManager.createQuery("SELECT p FROM Post p WHERE p.user.id = :user_id");
        query.setParameter("user_id", userId);
        return query.getResultList();
    }
}

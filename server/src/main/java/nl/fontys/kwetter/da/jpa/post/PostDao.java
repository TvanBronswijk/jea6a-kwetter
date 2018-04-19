package nl.fontys.kwetter.da.jpa.post;

import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.da.jpa.DataAccessBase;
import nl.fontys.kwetter.model.post.Post;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PostDao extends DataAccessBase<Post> implements PostDa {
    public PostDao() {
        super(Post.class);
    }

    @Override
    public List<Post> readAll() throws NoResultException {
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p", Post.class);
        return query.getResultList();
    }

    @Override
    public List<Post> readByUserId(Long userId) throws NoResultException {
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p WHERE p.user.id = :user_id", Post.class);
        query.setParameter("user_id", userId);
        return query.getResultList();
    }
}

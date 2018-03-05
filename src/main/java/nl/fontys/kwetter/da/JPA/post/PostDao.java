package nl.fontys.kwetter.da.JPA.post;

import nl.fontys.kwetter.da.JPA.DataAccessBase;
import nl.fontys.kwetter.da.JPA.JPA;
import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.model.post.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Stateless
@JPA
public class PostDao extends DataAccessBase<Post> implements PostDa {

    @PersistenceContext
    private EntityManager entityManager;

    public PostDao() {
        super(Post.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Collection<Post> readAll() {
        Query query = getEntityManager().createQuery("SELECT p FROM dbo.POST p");
        return query.getResultList();
    }
}

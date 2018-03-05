package nl.fontys.kwetter.da.JPA.post;

import nl.fontys.kwetter.da.JPA.DataAccessBase;
import nl.fontys.kwetter.da.JPA.JPA;
import nl.fontys.kwetter.da.inf.post.TagDa;
import nl.fontys.kwetter.model.post.Tag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@JPA
public class TagDao extends DataAccessBase<Tag> implements TagDa {

    @PersistenceContext
    private EntityManager entityManager;

    public TagDao() {
        super(Tag.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
package nl.fontys.kwetter.da.JPA.post;

import nl.fontys.kwetter.da.JPA.DataAccessBase;
import nl.fontys.kwetter.da.inf.post.TagDa;
import nl.fontys.kwetter.model.post.Tag;

import javax.ejb.Stateless;

@Stateless
public class TagDao extends DataAccessBase<Tag> implements TagDa {
    public TagDao() {
        super(Tag.class);
    }
}
package nl.fontys.kwetter.da.jpa.post;

import nl.fontys.kwetter.da.inf.post.TagDa;
import nl.fontys.kwetter.da.jpa.DataAccessBase;
import nl.fontys.kwetter.model.post.Tag;

import javax.ejb.Stateless;

@Stateless
public class TagDao extends DataAccessBase<Tag> implements TagDa {
    public TagDao() {
        super(Tag.class);
    }
}
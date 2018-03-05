package nl.fontys.kwetter.da.inf.post;

import nl.fontys.kwetter.da.inf.Crud;
import nl.fontys.kwetter.model.post.Post;

import java.util.Collection;

public interface PostDa extends Crud<Post> {
    Collection<Post> readAll();
}

package nl.fontys.kwetter.da.inf.post;

import nl.fontys.kwetter.da.inf.Crud;
import nl.fontys.kwetter.model.post.Post;

import java.util.List;

public interface PostDa extends Crud<Post> {
    List<Post> readAll();
    List<Post> readByUserId(Long userId);
    List<Post> search(String query);
}

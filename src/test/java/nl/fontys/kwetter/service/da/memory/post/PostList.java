package nl.fontys.kwetter.service.da.memory.post;

import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.service.da.memory.TestDataAccessBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostList extends TestDataAccessBase<Post> implements PostDa {
    @Override
    public List<Post> readAll() {
        List<Post> result = new ArrayList<>(data);
        result.removeAll(Collections.singleton(null));
        return result;
    }
}

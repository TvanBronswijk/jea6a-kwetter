package nl.fontys.kwetter.service.da.memory.post;

import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.service.da.memory.TestDataAccessBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostList extends TestDataAccessBase<Post> implements PostDa {
    @Override
    public List<Post> readAll() {
        List<Post> result = new ArrayList<>(data);
        result.removeAll(Collections.singleton(null));
        return result;
    }

    @Override
    public List<Post> readByUserId(Long userId) {
        List<Post> result = new ArrayList<>(data);
        result.removeAll(Collections.singleton(null));
        return result.stream().filter(p -> p.getUser().getId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<Post> search(String query) {
        return null;
    }
}

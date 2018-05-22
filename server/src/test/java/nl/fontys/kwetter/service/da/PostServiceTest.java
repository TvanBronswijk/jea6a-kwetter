package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.da.inf.post.TagDa;
import nl.fontys.kwetter.da.inf.user.UserDa;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.post.Tag;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.memory.post.PostList;
import nl.fontys.kwetter.service.da.memory.post.TagList;
import nl.fontys.kwetter.service.da.memory.user.UserList;
import nl.fontys.kwetter.service.helper.MentionService;
import nl.fontys.kwetter.service.helper.TagParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class PostServiceTest {

    @Spy
    private final PostDa postDa = new PostList();
    @Spy
    private final TagDa tagDa = new TagList();
    @Spy
    private final UserDa userDa = new UserList();
    @Spy
    private final TagParser tagParser = new TagParser();
    @Spy
    private final MentionService mentionService = new MentionService();

    @InjectMocks
    private PostService postService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPosts() {
        //Create a Post
        Post object = new Post();
        User user = new User();
        user.setId(1L);
        object.setUser(user);
        object.setContent("");
        object.setId(1L);
        postService.create(object);

        //Find a Post
        assertThat(postService.get(1L), is(object));

        //Posts by user
        assertThat(postService.getByUser(1L).size(), is(1));

        //Find All Posts
        assertThat(postService.getAll().size(), is(1));

        //Update Post
        object.setContent("Test Post");
        postService.update(object);
        assertThat(postService.get(1L), is(object));

        //Delete Post
        postService.delete(object);
        assertThat(postService.getAll().size(), is(0));
    }
}

package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.post.PostDa;
import nl.fontys.kwetter.da.inf.post.TagDa;
import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.post.Tag;
import nl.fontys.kwetter.model.user.User;
import nl.fontys.kwetter.service.da.memory.post.PostList;
import nl.fontys.kwetter.service.da.memory.post.TagList;
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
    private final TagParser tagParser = new TagParser();

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
        postService.createPost(object);

        //Find a Post
        assertThat(postService.readPost(1L), is(object));

        //Posts by user
        assertThat(postService.readPostsByUser(1L).size(), is(1));

        //Find All Posts
        assertThat(postService.readAllPosts().size(), is(1));

        //Update Post
        object.setContent("Test Post");
        postService.updatePost(object);
        assertThat(postService.readPost(1L), is(object));

        //Delete Post
        postService.deletePost(object);
        assertThat(postService.readAllPosts().size(), is(0));
    }

    @Test
    public void testTags() {
        //Create a Tag
        Tag object = new Tag();
        object.setId(1L);
        postService.createTag(object);

        //Find a Tag
        assertThat(postService.readTag(1L), is(object));

        //Update Tag
        object.setName("Test Tag");
        postService.updateTag(object);
        assertThat(postService.readTag(1L), is(object));

        //Delete Tag
        postService.deleteTag(object);
        assertThat(postService.readTag(1L), is(nullValue()));
    }
}

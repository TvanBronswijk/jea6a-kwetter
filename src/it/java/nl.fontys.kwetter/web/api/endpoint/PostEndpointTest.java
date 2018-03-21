package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.post.Post;
import nl.fontys.kwetter.model.user.User;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class PostEndpointTest {

    @Test
    public void restPostTest() {
        //create a Post
        Post newPost = new Post(null, "test", null);
        given().contentType("application/json").
                body(newPost)
                .when()
                .post("kwetter/api/post")
                .then()
                .statusCode(200);

        //get Post
        given().when()
                .get("kwetter/api/post/1")
                .then()
                .statusCode(200);

        //Like Post
        User user = new User();
        user.setId(1L);
        given().contentType("application/json").
                body(user)
                .when()
                .put("kwetter/api/post/1/like")
                .then()
                .statusCode(200);

        //Find By User
        given().when()
                .get("kwetter/api/post/user/1")
                .then()
                .statusCode(200);

        //update existing Post
        Post updatePost = new Post(null, "test2", null);
        updatePost.setId(1L);
        given().contentType("application/json")
                .body(updatePost)
                .when()
                .put("kwetter/api/post")
                .then()
                .statusCode(200);

        //remove Post
        given().when()
                .delete("kwetter/api/post/{id}", 1L)
                .then()
                .statusCode(200);


        //get all users
        given().when()
                .get("kwetter/api/post/all")
                .then()
                .statusCode(200);
    }
}
package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.post.Post;
import org.junit.Test;

import java.util.Date;

import static com.jayway.restassured.RestAssured.given;

public class PostEndpointTest {

    @Test
    public void restPostTest() {
        //create a User
        Post newPost = new Post(null, "test", new Date(), null);
        given().body(newPost)
                .when()
                .put("kwetter/api/post")
                .then()
                .statusCode(200);

        //get user
        given().when()
                .get("kwetter/api/post/1")
                .then()
                .statusCode(200);

        //update existing user
        Post updatePost = new Post(null, "test2", new Date(), null);
        updatePost.setId(1L);
        given().body(updatePost)
                .when()
                .put("kwetter/api/post")
                .then()
                .statusCode(200);

        //remove user
        Post removePost = new Post();
        removePost.setId(1L);
        given().body(removePost)
                .when()
                .delete("kwetter/api/post")
                .then()
                .statusCode(200);


        //get all users
        given().when()
                .get("kwetter/api/post/all")
                .then()
                .statusCode(200);
    }
}
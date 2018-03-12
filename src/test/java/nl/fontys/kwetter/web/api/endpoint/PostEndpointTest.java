package nl.fontys.kwetter.web.api.endpoint;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class PostEndpointTest {

    @Test
    public void getAllPosts() {
        when().get("/kwetter/api/post/all").
                then().
                statusCode(200);
    }

    @Test
    public void getSinglePost() {
        when().get("kwetter/api/post/{id}", 1).
                then().
                statusCode(200).
                body("content", equalTo("This is the first post on Kwetter! #first"));
    }
}
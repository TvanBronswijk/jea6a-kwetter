package nl.fontys.kwetter.web.api.endpoint;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class UserEndpointTest {

    @Test
    public void getAllUsers() {
        when().get("/kwetter/api/user/all").
                then().
                statusCode(200);
    }

    @Test
    public void getSingleUser() {
        when().get("kwetter/api/user/{id}", 1).
                then().
                statusCode(200).
                body("username", equalTo("first"));
    }
}
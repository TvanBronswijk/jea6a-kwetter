package nl.fontys.kwetter.web.api.endpoint;

import nl.fontys.kwetter.model.user.User;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.jayway.restassured.RestAssured.given;

public class UserEndpointTest {

    @Test
    public void restUserTest() {
        //create a User
        User newUser = new User("test", "test", "test@test.nl", null, null);
        given().contentType("application/json")
                .body(newUser)
                .when()
                .post("kwetter/api/user")
                .then()
                .statusCode(200);

        //get user
        given().when()
                .get("kwetter/api/user/1")
                .then()
                .statusCode(200);

        //update existing user
        User updateUser = new User("test2", "test", "test@test.nl", null, null);
        updateUser.setId(1L);
        given().contentType("application/json")
                .body(updateUser)
                .when()
                .put("kwetter/api/user")
                .then()
                .statusCode(200);

        //remove user
        given().when()
                .delete("kwetter/api/user/{id}", 1L)
                .then()
                .statusCode(200);


        //get all users
        given().when()
                .get("kwetter/api/user/all")
                .then()
                .statusCode(200);
    }

    @Test
    public void followTest() {
        throw new NotImplementedException();
    }

    @Test
    public void detailsTest() {
        throw new NotImplementedException();
    }
}
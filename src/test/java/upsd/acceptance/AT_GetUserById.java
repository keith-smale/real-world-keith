package upsd.acceptance;

import io.restassured.http.ContentType;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import upsd.domain.User;
import upsd.helpers.Helper;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

public class AT_GetUserById {

    @Test
    public void return_200_and_user_found_for_specified_id() {
        Helper.add(new User(1, "sam"));


        get("/users/1").then()
                .statusCode(HttpStatus.OK_200)
                .contentType(ContentType.JSON)
                .body("id", is(1))
                .body("name", is("sam"));
    }

    @Test
    public void return_404_and_no_user_found_for_specified_id() {
        get("/users/2").then()
                .statusCode(HttpStatus.NOT_FOUND_404);
    }

}

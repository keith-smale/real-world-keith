package upsd.acceptance;

import io.restassured.http.ContentType;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import upsd.domain.User;
import upsd.helpers.Helper;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

public class AT_GetUserByName {

    @Test
    public void return_200_and_user_found_for_specified_name() {
        Helper.add(new User(3,"neil"));

        get("/users/search?name=neil").then()
                .statusCode(HttpStatus.OK_200)
                .contentType(ContentType.JSON)
                .body("id", is(3))
                .body("name", is("neil"));
    }
}

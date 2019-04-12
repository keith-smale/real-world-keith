package upsd.controllers;

import com.eclipsesource.json.JsonObject;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import upsd.domain.User;
import upsd.repositories.UserRepository;

import java.util.Optional;

public class UserController {

    private static final String EMPTY_BODY = "";

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getById(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));

        Optional<User> userFound = userRepository.getBy(id);

        if (userFound.isPresent()) {
            res.type("application/json");
            return jsonStringFor(userFound.get());
        }

        res.status(HttpStatus.NOT_FOUND_404);
        return EMPTY_BODY;
    }

    public String getByName(Request req, Response res) {
        String name = req.params(":name");

        User userFound = userRepository.getName(name).get();

        res.type("application/json");
        return jsonStringFor(userFound);
    }

    private String jsonStringFor(User user) {
        return new JsonObject()
                .add("id", user.id())
                .add("name", user.name())
                .toString();
    }
}

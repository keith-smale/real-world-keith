package upsd.controllers;

import com.eclipsesource.json.JsonObject;
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

        res.status(404);
        return EMPTY_BODY;
    }

    private String jsonStringFor(User user) {
        return new JsonObject()
                .add("id", user.id())
                .add("name", user.name())
                .toString();
    }
}

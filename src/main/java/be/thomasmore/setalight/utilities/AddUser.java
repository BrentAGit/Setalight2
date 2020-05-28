package be.thomasmore.setalight.utilities;

import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Optional;

public class AddUser {

    public User addUser(Principal principal, UserRepository userRepository) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        User user = new User();
        if (!loggedInName.contains("nobody") || !loggedInName.isEmpty()) {
            Optional<User> userFromDb = userRepository.findUserByUsername(loggedInName);
            if (userFromDb.isPresent()) {
                user = userFromDb.get();
            }
        }
        return user;
    }
}

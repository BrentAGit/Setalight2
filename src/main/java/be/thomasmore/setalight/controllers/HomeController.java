package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.EventRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    private String application = "Setalight";

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        User user = new User();
        if (!loggedInName.contains("nobody") || !loggedInName.isEmpty()) {
            Optional<User> userFromDb = userRepository.findUserByUsername(loggedInName);
            user = userFromDb.get();
        }
        logger.info(String.format("logged in: %s",
                loggedInName));
        model.addAttribute("user", user);
        model.addAttribute("application", this.application);
        model.addAttribute("events", eventRepository.findAll());
        return "home";
    }

    @GetMapping({"/login"})
    public String login(Model model) {
        return "/login";
    }

    @GetMapping({"/logout"})
    public String logout(Model model) {
        return "/logout";
    }

}

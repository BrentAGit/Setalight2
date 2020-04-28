package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private String application = "Setalight";

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        logger.info(String.format("logged in: %s",
                loggedInName));
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

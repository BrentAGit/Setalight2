package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.Profile;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.EventRepository;
import be.thomasmore.setalight.repositories.ProfileRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import be.thomasmore.setalight.utilities.AddUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    private String application = "Setalight";

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping({"/", "/filter/{filter}"})
    public String home(@PathVariable(required = false) String filter,
                       Principal principal, Model model) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        User user = new User();
        if (!loggedInName.contains("nobody") || !loggedInName.isEmpty()) {
            Optional<User> userFromDb = userRepository.findUserByUsername(loggedInName);
            if (userFromDb.isPresent()) {
                user = userFromDb.get();
                if (user.getRole().contains("USER")) {
                } else if (user.getRole().contains("PRODUCTIEHUIS")) {
                    return "redirect:/productiehuis/";
                }
            }
        }
        Calendar calendar = Calendar.getInstance();
        Calendar secondDate = Calendar.getInstance();
        if (filter != null) {
            switch (filter) {
                case "all":
                    model.addAttribute("events", eventRepository.findAllByDateAfter(calendar.getTime()));
                    break;
                case "week":
                    secondDate.add(Calendar.WEEK_OF_YEAR, 1);
                    model.addAttribute("events", eventRepository.findAllByDateBetween(calendar.getTime(), secondDate.getTime()));
                    break;
                case "month":
                    secondDate.add(Calendar.MONTH, 1);
                    model.addAttribute("events", eventRepository.findAllByDateBetween(calendar.getTime(), secondDate.getTime()));
                    break;
            }
        } else model.addAttribute("events", eventRepository.findAllByDateAfter(calendar.getTime()));
        logger.info(String.format("logged in: %s",
                loggedInName));
        model.addAttribute("filterButtons", new String[]{"all", "week", "month"});
        model.addAttribute("user", user);
        model.addAttribute("application", this.application);
        return "home";
    }

    @GetMapping({"/week-calendar", "/week-calendar/{month}/{day}"})
    public String weekCalendar(@PathVariable(required = false) Integer day,
                               @PathVariable(required = false) Integer month,
                               Principal principal, Model model) {
        AddUser addUser = new AddUser();
        User user = addUser.addUser(principal, userRepository);
        if (user.getUsername() != null) {
            model.addAttribute("user", user);
            model.addAttribute("events", eventRepository.findAllByUsers(user));
        } else {
            model.addAttribute("events", eventRepository.findAll());
        }
        if (day != null && month != null) {
            model.addAttribute("month", month);
            model.addAttribute("day", day);
        }
        return "weekCalendar";
    }

    @GetMapping({"/logout"})
    public String logout(Model model) {
        return "logout";
    }

    @GetMapping("/denied")
    public String accesDenied() {
        return "denied";
    }


}

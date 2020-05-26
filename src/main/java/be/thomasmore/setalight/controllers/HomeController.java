package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.Profile;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.EventRepository;
import be.thomasmore.setalight.repositories.ProfileRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping({"/", "/{filter}"})
    public String home(@PathVariable(required = false) String filter,
                       Principal principal, Model model) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        User user = new User();
        if (!loggedInName.contains("nobody") || !loggedInName.isEmpty()) {
            Optional<User> userFromDb = userRepository.findUserByUsername(loggedInName);
            if (userFromDb.isPresent()) {
                user = userFromDb.get();
                if (user.getRole().contains("USER")) {
                    this.addRewards(user);
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
        }
        else model.addAttribute("events", eventRepository.findAllByDateAfter(calendar.getTime()));
        logger.info(String.format("logged in: %s",
                loggedInName));
        model.addAttribute("filterButtons", new String[] {"all", "week", "month"});
        model.addAttribute("user", user);
        model.addAttribute("application", this.application);
        return "home";
    }

    @GetMapping({"/login"})
    public String login(Model model) {
        return "login";
    }

    @GetMapping({"/logout"})
    public String logout(Model model) {
        return "logout";
    }

    @GetMapping("/denied")
    public String accedDenied() {
        return "redirect:/";
    }

    public void addRewards(User user){
        Optional<Profile> profileFromDb = profileRepository.findByUserId(user);
        Profile profile = new Profile();
        if (profileFromDb.isPresent()) profile = profileFromDb.get();
        Calendar calendar = Calendar.getInstance();
        List<Event> eventFromDb = eventRepository.findAllByUsersAndDateBefore(user, calendar.getTime());
        for (Event event:eventFromDb) {
            if (!profile.getCheckedEvents().contains(event)){
                profile.getCheckedEvents().add(event);
                profile.setRewardPoints(profile.getRewardPoints() + 20);
            }
        }
        profileRepository.save(profile);
    }
}

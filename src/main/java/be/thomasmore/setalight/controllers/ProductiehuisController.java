package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.EventRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/productiehuis")
public class ProductiehuisController {

    private Logger logger = LoggerFactory.getLogger(ProductiehuisController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/page/{ID}")
    public String profilePage(@PathVariable int ID, Model model) {
        Optional<User> userFromDb = userRepository.findById(ID);
        User user = userFromDb.get();
        model.addAttribute("user", user);
        return "productiehuis/TEST";
    }

    @GetMapping("/registerProductiehuis")
    public String registerProductiehuis(Principal principal, Model model) {
        User user = addUser(principal);
        model.addAttribute("user",user);
        return "productiehuis/registerProductiehuis";
    }

    @PostMapping("/registerProductiehuis")
    public String registeredProductiehuis(@RequestParam String username,
                                          @RequestParam String password,
                                          Model model) {
        logger.info(String.format("username= %s -- password= %s\n",
                username, password));
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("PRODUCTIEHUIS");
        user.setVerified(false);
        userRepository.save(user);
        autoLogin(username, password);
        return "redirect:/";
    }

    @GetMapping("/")
    public String homepageProductiehuis(Principal principal, Model model) {
        User user = addUser(principal);
        model.addAttribute("user",user);
            Collection<Event> events=eventRepository.findAllByCreatedBy(user);
            model.addAttribute("events",events);
        return "productiehuis/homepage";
    }

    private void autoLogin(String userName, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            logger.info("authentication: " + auth.isAuthenticated());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    private User addUser(Principal principal) {
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

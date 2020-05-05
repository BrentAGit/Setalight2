package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.User;
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

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String registerUser(Model model) {
        return "/user/register";
    }

    @PostMapping("/register")
    public String registered(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {
        logger.info(String.format("username= %s -- password= %s\n",
                username, password));
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        userRepository.save(user);
        autologin(username, password);
        return "redirect:/";
    }

    @GetMapping("/profilepage/{userId}")
    public String profile(@PathVariable int userId,
                          Model model) {
        Optional<User> userFromDb = userRepository.findById(userId);
        model.addAttribute("user", userFromDb.get());
        return "/user/profilepage";
    }

    @GetMapping("/edit-profile/{userId}")
    public String editProfile(@PathVariable int userId,
                              Model model) {
        Optional<User> userFromDb = userRepository.findById(userId);
        model.addAttribute("user", userFromDb.get());
        return "/user/edit-profile";
    }

    @PostMapping("/edit-profile/{userId}")
    public String editedProfile(@PathVariable int userId,
                                @RequestParam String username,
                                Model model) {
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) {
            user = userFromDb.get();
        }
        user.setUsername(username);
        userRepository.save(user);
        return "redirect:/user/profilepage/" + userId;
    }

    private void autologin(String userName, String password) {
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

}

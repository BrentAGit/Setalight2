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

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String registerPageAdmin(Principal principal, Model model) {
        addUser(principal, model);
        return "admin/register";
    }

    @PostMapping("/register")
    public String registerFormAdmin(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {
        logger.info(String.format("username= %s -- password= %s\n",
                username, password));
        User admin = new User();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole("ADMIN");
        userRepository.save(admin);
        autoLogin(username, password);
        return "redirect:/";
    }

    @GetMapping("/productiehuis/register")
    public String registerPageProductiehuis(Principal principal, Model model) {
        addUser(principal, model);
        return "admin/productiehuis-register";
    }

    @PostMapping("/productiehuis-register")
    public String registerFormProductiehuis(@RequestParam String username,
                                          @RequestParam String password,
                                          Model model) {
        logger.info(String.format("username= %s -- password= %s\n",
                username, password));
        User admin = new User();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole("PRODUCTIEHUIS");
        admin.setVerified(false);
        userRepository.save(admin);
        autoLogin(username, password);
        return "redirect:/";
    }

    @GetMapping({"/verifyproductiehuis"})
    public String findUnverifiedProductiehuis(Principal principal, Model model) {
        addUser(principal, model);
        model.addAttribute("productiehuizen", userRepository.findUserByRoleAndVerified("PRODUCTIEHUIS", false));
        return "admin/verifyproductiehuis";
    }

    @GetMapping({"/verify/{userId}"})
    public String verifySpecificProductiehuis(@PathVariable int userId, Model model) {
        Optional<User> userFromDb = userRepository.findById(userId);
        model.addAttribute("user", userFromDb.get());
        return "admin/verify";
    }

    @PostMapping("/verify/{userId}")
    public String verifyProductiehuis(@PathVariable int userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()){
            user = userFromDb.get();
        }
        user.setVerified(true);
        userRepository.save(user);

        return "redirect:/admin/verifyproductiehuis";
    }

    @GetMapping({"/testAdmin"})
    public String testAdmin(Model model) {
        return "admin/testAdmin";
    }

    private void autoLogin(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            logger.info("authentication: " + auth.isAuthenticated());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    private void addUser(Principal principal, Model model) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        User user = new User();
        if (!loggedInName.contains("nobody") || !loggedInName.isEmpty()) {
            Optional<User> userFromDb = userRepository.findUserByUsername(loggedInName);
            if (userFromDb.isPresent()) {
                user = userFromDb.get();
            }
        }
        model.addAttribute("user", user);
    }

}

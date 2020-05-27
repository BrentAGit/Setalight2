package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.ProductiehuisProfile;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.ProductiehuisProfileRepository;
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
    private ProductiehuisProfileRepository productiehuisProfileRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/productiehuis/register")
    public String registerPageProductiehuis(Principal principal, Model model) {
        addUser(principal, model);
        return "admin/productiehuis-register";
    }


    @GetMapping({"/verifyproductiehuis"})
    public String findUnverifiedProductiehuis(Principal principal, Model model) {
        addUser(principal, model);
        model.addAttribute("productiehuizenNotV", userRepository.findUserByRoleAndVerified("PRODUCTIEHUIS", false));
        model.addAttribute("productiehuizenV", userRepository.findUserByRoleAndVerified("PRODUCTIEHUIS", true));
        return "admin/verifyproductiehuis";
    }

    @GetMapping({"/verify/{userId}"})
    public String verifySpecificProductiehuis(@PathVariable int userId, Model model) {
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) user = userFromDb.get();
        Optional<ProductiehuisProfile> profileFromDb = productiehuisProfileRepository.findByUserId(user);
        ProductiehuisProfile profile = new ProductiehuisProfile();
        if (profileFromDb.isPresent()) profile = profileFromDb.get();
        model.addAttribute("profile", profile);
        model.addAttribute("user", user);
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

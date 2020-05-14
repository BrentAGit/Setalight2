package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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

    @Value("${upload.images.dir}")
    private String uploadImagesDirString;

    @GetMapping("/register")
    public String registerUser(Model model) {
        return "/user/register";
    }

    @PostMapping("/register")
    public String registered(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate,
                             @RequestParam String email,
                             @RequestParam String haircolor,
                             @RequestParam MultipartFile profilepicture,
                             @RequestParam MultipartFile fullpicture,
                             @RequestParam Double length ,
                             Model model) {
        logger.info(String.format("username= %s -- password= %s -- birthdate=%s\n",
                username, password, birthdate));
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setBirthdate(birthdate);
        String profilePictureName = profilepicture.getOriginalFilename();
        if(!profilePictureName.equals(user.getProfilepicture())){
            File imageFileDir= new File(uploadImagesDirString);
            if(!imageFileDir.exists()){
                imageFileDir.mkdir();
            }
            File imageFile= new File(uploadImagesDirString,profilePictureName);
                try {
                    profilepicture.transferTo(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

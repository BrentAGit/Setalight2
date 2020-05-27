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
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${upload.images.dir}")
    private String uploadImagesDirString;

    @GetMapping("/register")
    public String registerUser(Model model) {
        return "user/register";
    }

    @PostMapping("/register")
    public String registered(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String hairColor,
                             @RequestParam(required = false) MultipartFile profilePicture,
                             @RequestParam(required = false) MultipartFile fullPicture,
                             @RequestParam(required = false) Double length,
                             @RequestParam(required = false) String nationalInsuranceNumber,
                             Model model) {
        logger.info(String.format("username= %s -- password= %s\n",
                username, password));
        User user = new User();
        Profile profile = new Profile();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        profile.setUserId(user);
        if (!(birthDate == null && email.isEmpty() && hairColor.isEmpty() && profilePicture.isEmpty() && fullPicture.isEmpty() && length == null && nationalInsuranceNumber.isEmpty())) {
            profile.setBirthDate(birthDate);
            profile.setEmail(email);
            profile.setHairColor(hairColor);
            profile.setLength(length);
            profile.setNationalInsuranceNumber(nationalInsuranceNumber);
            profile.setProfilePicture(fileUpload(profile, profilePicture));
            profile.setFullPicture(fileUpload(profile, fullPicture));
        }
        userRepository.save(user);
        profileRepository.save(profile);
        autoLogin(username, password);
        return "redirect:/";
    }

    @GetMapping("/profilepage/{userId}")
    public String profile(@PathVariable int userId,
                          Model model) {
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) user = userFromDb.get();
        Optional<Profile> profileFromDb = profileRepository.findByUserId(user);
        Profile profile = new Profile();
        if (profileFromDb.isPresent()) profile = profileFromDb.get();
        Calendar calendar = Calendar.getInstance();
        List<Event> eventsFromDb = eventRepository.findAllByUsersAndDateAfter(user, calendar.getTime());
        model.addAttribute("user", user);
        model.addAttribute("profile", profile);
        model.addAttribute("events", eventsFromDb);
        return "user/profilepage";
    }

    @GetMapping("/edit-profile/{userId}")
    public String editProfile(@PathVariable int userId,
                              Model model) {
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) user = userFromDb.get();
        Optional<Profile> profileFromDb = profileRepository.findByUserId(user);
        Profile profile = new Profile();
        if (profileFromDb.isPresent()) profile = profileFromDb.get();
        model.addAttribute("user", user);
        model.addAttribute("profile", profile);
        return "user/edit-profile";
    }

    @GetMapping("/addFriends/{userId}")
    public String addFriendsPage(@PathVariable int userId,
                                Model model){
        Profile profile = getProfile(userId);
        Iterable<Profile> profiles = profileRepository.findAll();
        ArrayList<Profile> noFriends = new ArrayList<>();
        for (Profile friendProfile : profiles) {
            if (!profile.getFriends().contains(friendProfile)) {
                noFriends.add(friendProfile);
            }
        }
        model.addAttribute("friends",noFriends);
        model.addAttribute("currentProfile",profile);
        model.addAttribute("user", profile.getUserId());
        return "/user/addFriends";
    }

    @PostMapping("/addFriends/{userId}/{user}")
    public String addFriends(@PathVariable int userId,
                             @PathVariable int user,
                             Model model){
        Profile currentProfile=getProfile(userId);
        Profile friendProfile=getProfile(user);
        currentProfile.getFriends().add(friendProfile);
        profileRepository.save(currentProfile);
        return "redirect:/user/addFriends/"+userId;
    }

    @PostMapping("/edit-profile/{userId}")
    public String editedProfile(@PathVariable int userId,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
                                @RequestParam String email,
                                @RequestParam String hairColor,
                                @RequestParam MultipartFile profilePicture,
                                @RequestParam MultipartFile fullPicture,
                                @RequestParam Double length,
                                @RequestParam String nationalInsuranceNumber,
                                Model model) {
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) user = userFromDb.get();
        Optional<Profile> profileFromDb = profileRepository.findByUserId(user);
        Profile profile = new Profile();
        if (profileFromDb.isPresent()) profile = profileFromDb.get();
        profile.setBirthDate(birthDate);
        profile.setEmail(email);
        profile.setHairColor(hairColor);
        profile.setLength(length);
        profile.setNationalInsuranceNumber(nationalInsuranceNumber);
        profile.setProfilePicture(fileUpload(profile, profilePicture));
        profile.setFullPicture(fileUpload(profile, fullPicture));
        profileRepository.save(profile);
        userRepository.save(user);
        return "redirect:/user/profilepage/" + userId;
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

    private String fileUpload(Profile profile, MultipartFile picture) {
        String name = picture.getOriginalFilename();
        File imageFileDir = new File(uploadImagesDirString);
        if (!imageFileDir.exists()) {
            imageFileDir.mkdirs();
        }
        File imageFile = new File(uploadImagesDirString, name);
        try {
            picture.transferTo(imageFile);

            return "/" + name;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Profile getProfile(int userId) {
        Optional<User> userFromDb=userRepository.findUserById(userId);
        User user= new User();
        if(userFromDb.isPresent()){
            user= userFromDb.get();
        }
        Optional<Profile> profileFromDb=profileRepository.findByUserId(user);
        Profile profile= new Profile();
        if(profileFromDb.isPresent()){
            profile= profileFromDb.get();
        }
        return profile;
    }

}

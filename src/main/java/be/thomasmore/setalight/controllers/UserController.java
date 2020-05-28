package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.Profile;
import be.thomasmore.setalight.models.Reward;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.EventRepository;
import be.thomasmore.setalight.repositories.ProfileRepository;
import be.thomasmore.setalight.repositories.RewardRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import be.thomasmore.setalight.utilities.AutoLogin;
import be.thomasmore.setalight.utilities.FileUploader;
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

import javax.persistence.Entity;
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
    private RewardRepository rewardRepository;

    @Autowired
    private AuthenticationManager authenticationManager;



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
            FileUploader fileUploader= new FileUploader();
            profile.setProfilePicture(fileUploader.fileUpload(profile, profilePicture));
            profile.setFullPicture(fileUploader.fileUpload(profile, fullPicture));
        }
        userRepository.save(user);
        profileRepository.save(profile);
        AutoLogin autoLogin = new AutoLogin();
        autoLogin.autoLogin(username, password);
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
        ArrayList<Reward> rewardsFromDb = (ArrayList<Reward>) rewardRepository.findAll();
        ArrayList<Reward> rewards = new ArrayList<>();
        for (Reward reward : rewardsFromDb) {
            if (!profile.getBoughtRewards().contains(reward)) {
                rewards.add(reward);
            }
        }
        Calendar calendar = Calendar.getInstance();
        List<Event> eventsFromDb = eventRepository.findAllByUsersAndDateAfter(user, calendar.getTime());
        model.addAttribute("user", user);
        model.addAttribute("profile", profile);
        model.addAttribute("events", eventsFromDb);
        model.addAttribute("rewards", rewards);
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
                                 Model model) {
        Profile profile = getProfile(userId);
        Iterable<Profile> profiles = profileRepository.findAll();
        ArrayList<Profile> noFriends = new ArrayList<>();
        for (Profile friendProfile : profiles) {
            if (!profile.getFriends().contains(friendProfile)) {
                noFriends.add(friendProfile);
            }
        }
        model.addAttribute("friends", noFriends);
        model.addAttribute("currentProfile", profile);
        model.addAttribute("user", profile.getUserId());
        return "/user/addFriends";
    }

    @PostMapping("/addFriends/{userId}/{user}")
    public String addFriends(@PathVariable int userId,
                             @PathVariable int user,
                             Model model) {
        Profile currentProfile = getProfile(userId);
        Profile friendProfile = getProfile(user);
        currentProfile.getFriends().add(friendProfile);
        profileRepository.save(currentProfile);
        return "redirect:/user/addFriends/" + userId;
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
        FileUploader fileUploader=new FileUploader();
        profile.setProfilePicture(fileUploader.fileUpload(profile, profilePicture));
        profile.setFullPicture(fileUploader.fileUpload(profile, fullPicture));
        profileRepository.save(profile);
        userRepository.save(user);
        return "redirect:/user/profilepage/" + userId;
    }

    @PostMapping("/buy-rewards/{userId}/{rewardId}")
    private String buyRewards(@PathVariable int userId,
                              @PathVariable int rewardId) {
        Profile profile = getProfile(userId);
        Optional<Reward> rewardFromDb = rewardRepository.findById(rewardId);
        Reward reward = new Reward();
        if (rewardFromDb.isPresent()) reward = rewardFromDb.get();
        profile.setRewardPoints(profile.getRewardPoints() - reward.getPoints());
        profile.getBoughtRewards().add(reward);
        profileRepository.save(profile);
        rewardRepository.save(reward);
        return "redirect:/user/profilepage/" + userId;
    }

    private Profile getProfile(int userId) {
        Optional<User> userFromDb = userRepository.findUserById(userId);
        User user = new User();
        if (userFromDb.isPresent()) {
            user = userFromDb.get();
        }
        Optional<Profile> profileFromDb = profileRepository.findByUserId(user);
        Profile profile = new Profile();
        if (profileFromDb.isPresent()) {
            profile = profileFromDb.get();
        }
        return profile;
    }

}

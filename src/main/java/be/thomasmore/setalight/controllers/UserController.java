package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.Profile;
import be.thomasmore.setalight.models.Reward;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.EventRepository;
import be.thomasmore.setalight.repositories.ProfileRepository;
import be.thomasmore.setalight.repositories.RewardRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import be.thomasmore.setalight.utilities.AddUser;
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
import java.security.Principal;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Value("${upload.images.dir}")
    private String uploadImagesDirString = "${upload.images.dir}";

    @GetMapping("/profilepage/{userId}")
    public String profile(@PathVariable int userId,
                          Principal principal,
                          Model model) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) user = userFromDb.get();
        if (loggedInName.contains("nobody") || user.getUsername().contains(loggedInName)) {
            model.addAttribute("canEdit", false);
        } else {
            model.addAttribute("canEdit", true);
        }
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
        return "user/addFriends";
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
        FileUploader fileUploader = new FileUploader();
        profile.setProfilePicture(fileUploader.fileUpload(profilePicture, uploadImagesDirString));
        profile.setFullPicture(fileUploader.fileUpload(fullPicture, uploadImagesDirString));
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
        return "redirect:/user/rewards/" + userId;
    }

    @GetMapping("/rewards/{userId}")
    public String rewards(@PathVariable int userId, Model model, Principal principal) {
        Profile profile = getProfile(userId);
        ArrayList<Reward> rewardsFromDb = (ArrayList<Reward>) rewardRepository.findAll();
        ArrayList<Reward> rewards = new ArrayList<>();
        for (Reward reward : rewardsFromDb) {
            if (!profile.getBoughtRewards().contains(reward)) {
                rewards.add(reward);
            }
        }
        AddUser adduser = new AddUser();
        model.addAttribute("user", adduser.addUser(principal, userRepository));
        model.addAttribute("rewards", rewards);
        model.addAttribute("profile", profile);
        return "user/rewardPage";
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

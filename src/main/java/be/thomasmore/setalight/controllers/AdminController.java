package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.ProductiehuisProfile;
import be.thomasmore.setalight.models.Reward;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.ProductiehuisProfileRepository;
import be.thomasmore.setalight.repositories.RewardRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import be.thomasmore.setalight.utilities.AddUser;
import be.thomasmore.setalight.utilities.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private RewardRepository rewardRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${upload.images.dir}")
    private String uploadImagesDirString = "${upload.images.dir}";

    @GetMapping({"/verifyproductiehuis"})
    public String findUnverifiedProductiehuis(Principal principal, Model model) {
        AddUser addUser = new AddUser();
        User user = addUser.addUser(principal, userRepository);
        model.addAttribute("user", user);
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
        ProductiehuisProfile productiehuisProfile = new ProductiehuisProfile();
        if (profileFromDb.isPresent()) productiehuisProfile = profileFromDb.get();
        model.addAttribute("productiehuisProfile", productiehuisProfile);
        model.addAttribute("user", user);
        return "admin/verify";
    }

    @PostMapping("/verify/{userId}")
    public String verifyProductiehuis(@PathVariable int userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) {
            user = userFromDb.get();
        }
        user.setVerified(true);
        userRepository.save(user);

        return "redirect:/admin/verifyproductiehuis";
    }

    @GetMapping("/create-reward")
    public String createReward(Principal principal , Model model) {
        AddUser addUser = new AddUser();
        User user = addUser.addUser(principal, userRepository);
        model.addAttribute("user", user);
        return "admin/createReward";
    }

    @PostMapping("/create-reward")
    public String rewardCreated(@RequestParam String name,
                                @RequestParam int points,
                                @RequestParam MultipartFile picture, Principal principal) {
        AddUser addUser = new AddUser();
        User user = addUser.addUser(principal, userRepository);
        Reward reward = new Reward();
        reward.setName(name);
        reward.setPoints(points);
        FileUploader fileUploader = new FileUploader();
        reward.setPicture(fileUploader.fileUpload(picture, uploadImagesDirString));
        rewardRepository.save(reward);
        return "redirect:/user/rewards/" + user.getId();
    }
}

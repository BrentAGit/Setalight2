package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Profile;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.ProfileRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import be.thomasmore.setalight.utilities.AutoLogin;
import be.thomasmore.setalight.utilities.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Controller

public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Value("${upload.images.dir}")
    private String uploadImagesDirString = "${upload.images.dir}";

    @GetMapping({"/login"})
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user/register")
    public String registerUser(Model model) {
        return "user/register";
    }

    @PostMapping("/user/register")
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
            profile.setProfilePicture(fileUploader.fileUpload(profilePicture, uploadImagesDirString));
            profile.setFullPicture(fileUploader.fileUpload(fullPicture, uploadImagesDirString));
        }
        userRepository.save(user);
        profileRepository.save(profile);
        AutoLogin autoLogin = new AutoLogin();
        autoLogin.autoLogin(username, password, authenticationManager);
        return "redirect:/";
    }
}

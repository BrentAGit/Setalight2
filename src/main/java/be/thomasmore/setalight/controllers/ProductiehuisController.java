package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.ProductiehuisProfile;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.EventRepository;
import be.thomasmore.setalight.repositories.ProductiehuisProfileRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import be.thomasmore.setalight.utilities.AddUser;
import be.thomasmore.setalight.utilities.AutoLogin;
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
import java.util.List;
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

    @Autowired
    private ProductiehuisProfileRepository productiehuisProfileRepository;

    @GetMapping("/page/{ID}")
    public String profilePage(@PathVariable int ID, Model model) {
        Optional<User> userFromDb = userRepository.findById(ID);
        User user = userFromDb.get();
        model.addAttribute("user", user);
        return "productiehuis/TEST";
    }

    @GetMapping("/")
    public String homepageProductiehuis(Principal principal, Model model) {
        AddUser addUser=new AddUser();
        User user = addUser.addUser(principal, userRepository);
        model.addAttribute("user", user);
        logger.info(String.format("name=%s",
                user.getUsername()));
        if (user.getUsername() != null) {
            List<Event> events = eventRepository.findAllByCreatedBy(user);
            model.addAttribute("events", events);
        }
        return "productiehuis/homepage";
    }

    @GetMapping("/edit-profilepage/{userId}")
    public String editProfilepage(@PathVariable int userId, Model model){
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) user = userFromDb.get();
        Optional<ProductiehuisProfile> ProductiehuisProfileFromDb = productiehuisProfileRepository.findByUserId(user);
        ProductiehuisProfile ProductiehuisProfile = new ProductiehuisProfile();
        if (ProductiehuisProfileFromDb.isPresent()) ProductiehuisProfile = ProductiehuisProfileFromDb.get();

        model.addAttribute("user", user);
        model.addAttribute("productiehuisProfile", ProductiehuisProfile);

        return "productiehuis/edit-profilepage";
    }
    @PostMapping("/edit-profilepage/{userId}")
    public String editProfilepageProductie(@PathVariable int userId,
                                          @RequestParam String nameCompany,
                                          @RequestParam String description,
                                          @RequestParam String nameOwner,
                                          @RequestParam String companyNumber,
                                          @RequestParam String province,
                                          @RequestParam String city,
                                          @RequestParam String street,
                                          @RequestParam String postalcode,
                                          @RequestParam String houseNumber,
                                          Model model) {
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) user = userFromDb.get();
        Optional<ProductiehuisProfile> productieProfileFromDb = productiehuisProfileRepository.findByUserId(user);
        ProductiehuisProfile productiehuisProfile = new ProductiehuisProfile();
        if (productieProfileFromDb.isPresent()) productiehuisProfile = productieProfileFromDb.get();

        productiehuisProfile.setNameCompany(nameCompany);
        productiehuisProfile.setDescription(description);
        productiehuisProfile.setNameOwner(nameOwner);
        productiehuisProfile.setCompanyNumber(companyNumber);
        productiehuisProfile.setProvince(province);
        productiehuisProfile.setCity(city);
        productiehuisProfile.setStreet(street);
        productiehuisProfile.setPostalCode(postalcode);
        productiehuisProfile.setHouseNumber(houseNumber);

        userRepository.save(user);
        productiehuisProfileRepository.save(productiehuisProfile);
        return "redirect:/productiehuis/profilepageProductiehuis/" + userId;
    }


    @GetMapping("/profilepageProductiehuis/{userId}")
        public String profilepageProductihuis(@PathVariable int userId, Model model){
        Optional<User> userFromDb = userRepository.findById(userId);
        User user = new User();
        if (userFromDb.isPresent()) user = userFromDb.get();
        Optional<ProductiehuisProfile> ProductiehuisProfileFromDb = productiehuisProfileRepository.findByUserId(user);
        ProductiehuisProfile ProductiehuisProfile = new ProductiehuisProfile();
        if (ProductiehuisProfileFromDb.isPresent()) ProductiehuisProfile = ProductiehuisProfileFromDb.get();

        model.addAttribute("user", user);
        model.addAttribute("productiehuisProfile", ProductiehuisProfile);

        return "productiehuis/profilepageProductiehuis";
    }

    @GetMapping("/aanwezigen/{eventId}")
        public String aanwezigen(@PathVariable int eventId, Model model){
        return "productiehuis/whoIsGoingPage";
    }
}

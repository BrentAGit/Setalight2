package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.ProductiehuisProfile;
import be.thomasmore.setalight.models.Profile;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.EventRepository;
import be.thomasmore.setalight.repositories.ProductiehuisProfileRepository;
import be.thomasmore.setalight.repositories.ProfileRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import be.thomasmore.setalight.utilities.AddUser;
import be.thomasmore.setalight.utilities.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/event")
public class EventController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private String application = "Setalight";

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductiehuisProfileRepository productiehuisProfileRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Value("${upload.images.dir}")
    private String uploadImagesDirString;

    @GetMapping("/event")
    public String home(Principal principal, Model model) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        logger.info(String.format("logged in: %s",
                loggedInName));
        Date date = java.sql.Date.valueOf(LocalDate.now());
        addUser(principal, model);
        model.addAttribute("application", this.application);
        model.addAttribute("events", eventRepository.findAllByDateAfter(date));
        return "event";
    }

    @GetMapping("/events")
    public String events(Principal principal, Model model) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        logger.info(String.format("logged in: %s",
                loggedInName));
        Date date = java.sql.Date.valueOf(LocalDate.now());
        Optional<User> user =userRepository.findUserByUsername(loggedInName);
        model.addAttribute("application", this.application);
        model.addAttribute("events", eventRepository.findAllByDateAfterAndUsers(date,user.get()));
        addUser(principal, model);
        return "events";
    }

    @GetMapping({"/events/{eventsId}"})
    public String userEvent(@PathVariable int eventsId,
                            Principal principal,
                            Model model) {
        Optional<Event> eventFromDb = eventRepository.findById(eventsId);
        Event event = new Event();
        if (eventFromDb.isPresent()) event = eventFromDb.get();
        User productiehuis = event.getCreatedBy();
        ProductiehuisProfile productiehuisProfile = new ProductiehuisProfile();
        Optional<ProductiehuisProfile> productiehuisProfileFromDB = productiehuisProfileRepository.findByUserId(productiehuis);
        if(productiehuisProfileFromDB.isPresent())productiehuisProfile = productiehuisProfileFromDB.get();

        AddUser adduser = new AddUser();
        User user = adduser.addUser(principal, userRepository);
        if (event.getUsers().contains(user)){
            model.addAttribute("Going", true);
        }
        else{
            model.addAttribute("Going", false);
        }

        model.addAttribute("event", event);
        model.addAttribute("productiehuisProfile" ,productiehuisProfile);
        model.addAttribute("productihuis",productiehuis);
        addUser(principal, model);
        return "userEvent";
    }

    @PostMapping({"/events/{eventsId}"})
    public String goingEvent(@PathVariable int eventsId, Principal principal,
                             Model model) {
        Optional<Event> eventFromDb = eventRepository.findById(eventsId);
        Optional<User> userFromDB = userRepository.findUserByUsername(principal.getName());
        User user = new User();
        Event event = new Event();
        if (eventFromDb.isPresent()) event = eventFromDb.get();
        if (userFromDB.isPresent()) user = userFromDB.get();
        if (!event.getUsers().contains(user)) {
            event.getUsers().add(user);
        }

        eventRepository.save(event);
        return "redirect:/";
    }

    @PostMapping({"event"})
    public String createEvent(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Integer amountOfParticipants,
                              @RequestParam String typeWanted,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                              @RequestParam String startTime,
                              @RequestParam String endTime,
                              @RequestParam String city,
                              @RequestParam String postcode,
                              @RequestParam String street,
                              @RequestParam String houseNumber,
                              @RequestParam MultipartFile picture,
                              Principal principal,
                              Model model) throws ParseException {
        logger.info(String.format("new name=%s -- new date=%S -- new artists=%d\n",
                name, description, amountOfParticipants
        ));
        Optional<User> userFromDB = userRepository.findUserByUsername(principal.getName());
        User user = new User();
        if (userFromDB.isPresent()){user = userFromDB.get();}
        Event event = new Event();
        event.setName(name);
        event.setAmountOfParticipants(amountOfParticipants);
        event.setDescription(description);
        event.setTypeWanted(typeWanted);
        event.setDate(date);
        event.setStartTime(LocalTime.parse(startTime));
        event.setEndTime(LocalTime.parse(endTime));
        event.setCity(city);
        event.setPostcode(postcode);
        event.setStreet(street);
        event.setHouseNumber(houseNumber);
        event.setCreatedBy(user);
        event.setControl(false);
        event.setRewardCode(createEventCode(user));
        FileUploader fileUploader = new FileUploader();
        event.setPicture(fileUploader.fileUpload(picture,uploadImagesDirString));
        eventRepository.save(event);

        return "redirect:/";
    }

    public String createEventCode(User user){
        char first =user.getUsername().charAt(0);

        int random = (int)(Math.random() * 99999 + 1);
        String rewardcode = String.valueOf(first) + random;
        List<Event> eventsFromDB = eventRepository.findAllByCreatedBy(user);
        for (Event event : eventsFromDB){
            if(event.getRewardCode().equals(rewardcode)){
                random++;
                rewardcode = String.valueOf(first) + random;
            }
        }
        return rewardcode;
    }

    @GetMapping({"/edit-event/{eventId}"})
    public String editGetEvent(@PathVariable(required = false) int eventId,
                               Principal principal,
                               Model model) {

        Optional<Event> event = eventRepository.findById(eventId);
        Event event1 = event.get();
        addUser(principal, model);
        model.addAttribute("event", event1);
        return "edit-event";
    }

    @PostMapping({"/edit-event/{eventId}"})
    public String editPostEvent(@PathVariable(required = false) int eventId,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam Integer amountOfParticipants,
                                @RequestParam String typeWanted,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                @RequestParam String startTime,
                                @RequestParam String endTime,
                                @RequestParam String city,
                                @RequestParam String postcode,
                                @RequestParam String street,
                                @RequestParam String houseNumber,
                                @RequestParam MultipartFile picture,
                                Principal principal,
                                Model model) {
        logger.info(String.format("new name=%s -- new date=%S -- new artists=%d\n",
                name, description, amountOfParticipants
        ));
        Optional<Event> eventDromDB = eventRepository.findById(eventId);
        if (eventDromDB.isPresent()) {
            Event event = eventDromDB.get();
            event.setName(name);
            event.setAmountOfParticipants(amountOfParticipants);
            event.setDescription(description);
            event.setTypeWanted(typeWanted);
            event.setDate(date);
            event.setStartTime(LocalTime.parse(startTime));
            event.setEndTime(LocalTime.parse(endTime));
            event.setCity(city);
            event.setPostcode(postcode);
            event.setStreet(street);
            event.setHouseNumber(houseNumber);
            String pictureName = picture.getOriginalFilename();
            if (!pictureName.equals(event.getPicture())) {
                File imageFileDir = new File(uploadImagesDirString);
                if (!imageFileDir.exists()) {
                    imageFileDir.mkdirs();
                }
                File imageFile = new File(uploadImagesDirString, pictureName);
                try {
                    picture.transferTo(imageFile);
                    event.setPicture("/" + pictureName);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            eventRepository.save(event);
        }
        addUser(principal, model);
        return "redirect:/event/events/{eventId}";
    }

    @PostMapping("/canceled/{eventId}")
    public String canceledEvent(@PathVariable int eventId){
        Optional<Event> eventFromDb = eventRepository.findById(eventId);
        Event event = new Event();
        if(eventFromDb.isPresent()){
            event = eventFromDb.get();
        }
        event.setCanceled(true);
        eventRepository.save(event);
        return "redirect:/event/events/" + eventId;
    }

    @GetMapping("/invite/{eventId}")
    public String invite(Model model, Principal principal, @PathVariable int eventId){
        AddUser adduser = new AddUser();
        User user = adduser.addUser(principal, userRepository);
        Optional<Profile> profileFromDb = profileRepository.findByUserId(user);
        Profile profile = new Profile();
        if (profileFromDb.isPresent()){
            profile = profileFromDb.get();
        }
        ArrayList<Profile> friends = new ArrayList<>();
        for (Profile p : profile.getFriends()){
            if (!profile.getAlreadyInvitedUsers().contains(p)){
                friends.add(p);
            }
        }
        model.addAttribute("friends", friends);
        model.addAttribute("user", user);
        model.addAttribute("eventId", eventId);
        return "event/inviteFriends";
    }

    @PostMapping("/inviteFriends/{eventId}/{userId}")
    public String inviteUser(@PathVariable int eventId, @PathVariable int userId, Principal principal){
        Optional<Profile> profileFromDb = profileRepository.findByUserId(userRepository.findUserById(userId).get());
        Profile profile = new Profile();
        if (profileFromDb.isPresent()){
            profile = profileFromDb.get();
        }
        Optional<Event> eventFromDb = eventRepository.findById(eventId);
        Event event = new Event();
        if (eventFromDb.isPresent()){
            event = eventFromDb.get();
        }
        profile.getInvitedEvents().add(event);
        AddUser addUser = new AddUser();
        Optional<Profile> currentProfileFromDb = profileRepository.findByUserId(addUser.addUser(principal, userRepository));
        Profile currentProfile = new Profile();
        if (currentProfileFromDb.isPresent()){
            currentProfile = currentProfileFromDb.get();
        }
        profile.getInvitedBy().add(currentProfile);
        currentProfile.getAlreadyInvitedUsers().add(profile);
        profileRepository.save(currentProfile);
        profileRepository.save(profile);
        return "redirect:/event/invite/" + eventId;
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

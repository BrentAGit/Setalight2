package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.models.User;
import be.thomasmore.setalight.repositories.EventRepository;
import be.thomasmore.setalight.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;
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
        model.addAttribute("event", event);
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
        eventRepository.save(event);


        return "redirect:/";
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
            String name = picture.getOriginalFilename();
            if (!name.equals(event.)) {
                File imageFileDir = new File(uploadImagesDirString);
                if (!imageFileDir.exists()) {
                    imageFileDir.mkdirs();
                }
                File imageFile = new File(uploadImagesDirString, name);
                try {
                    picture.transferTo(imageFile);
                    switch (cindOfPicture) {
                        case 0:
                            profile.setProfilePicture("/" + name);
                            break;
                        case 1:
                            profile.setFullPicture("/" + name);
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            eventRepository.save(event);
        }
        addUser(principal, model);
        return "redirect:/event/events/{eventId}";
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

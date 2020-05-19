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

import java.security.Principal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
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
        model.addAttribute("application", this.application);
        model.addAttribute("events", eventRepository.findAllByDateAfter(date));
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
        event.getUsers().add(user);
        eventRepository.save(event);
        return "redirect:/";
    }

    @PostMapping({"event"})
    public String createEvent(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Integer aantaldeelnemers,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date date,
                              @RequestParam String startTime,
                              @RequestParam String endTime,
                              @RequestParam String city,
                              @RequestParam String postcode,
                              @RequestParam String street,
                              @RequestParam String houseNumber,
                              Model model) throws ParseException {
        logger.info(String.format("new name=%s -- new date=%S -- new artists=%d\n",
                name, description, aantaldeelnemers
        ));
        Event event = new Event();
        event.setName(name);
        event.setAantaldeelnemers(aantaldeelnemers);
        event.setDescription(description);
        event.setDatum(date);
        event.setStartTime(LocalTime.parse(startTime));
        event.setEndTime(LocalTime.parse(endTime));
        event.setCity(city);
        event.setPostcode(postcode);
        event.setStreet(street);
        event.setHousenumber(houseNumber);
        event.setControle(false);
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
                                @RequestParam Integer aantaldeelnemers,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date date,
                                @RequestParam String startTime,
                                @RequestParam String endTime,
                                @RequestParam String city,
                                @RequestParam String postcode,
                                @RequestParam String street,
                                @RequestParam String houseNumber,
                                Principal principal,
                                Model model) {
        logger.info(String.format("new name=%s -- new date=%S -- new artists=%d\n",
                name, description, aantaldeelnemers
        ));
        Optional<Event> eventDromDB = eventRepository.findById(eventId);
        if (eventDromDB.isPresent()) {
            Event event = eventDromDB.get();
            event.setName(name);
            event.setAantaldeelnemers(aantaldeelnemers);
            event.setDescription(description);
            event.setDatum(date);
            event.setStartTime(LocalTime.parse(startTime));
            event.setEndTime(LocalTime.parse(endTime));
            event.setCity(city);
            event.setPostcode(postcode);
            event.setStreet(street);
            event.setHousenumber(houseNumber);
            eventRepository.save(event);
        }
        addUser(principal, model);
        return "redirect:/event/events";
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

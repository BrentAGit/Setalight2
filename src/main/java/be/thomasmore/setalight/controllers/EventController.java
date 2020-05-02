package be.thomasmore.setalight.controllers;

import be.thomasmore.setalight.models.Event;
import be.thomasmore.setalight.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/event")
public class EventController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private String application = "Setalight";

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/event")
    public String home(Principal principal, Model model) {
        String loggedInName = principal != null ? principal.getName() : "nobody";
        logger.info(String.format("logged in: %s",
                loggedInName));
        model.addAttribute("application", this.application);
        model.addAttribute("events", eventRepository.findAll());
        return "event";
    }
    @PostMapping({"/event"})
    public String createEvent(@RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam Integer aantaldeelnemers,
                                  Model model) {
        logger.info(String.format("new name=%s -- new date=%S -- new artists=%d\n",
                name, description,aantaldeelnemers
        ));
        Event event = new Event();
        event.setName(name);
        event.setAantaldeelnemers(aantaldeelnemers);
        event.setDescription(description);
        event.setControle(false);
        eventRepository.save(event);


        return "redirect:/";
    }

    @GetMapping({"/edit-event/{eventId}"})
    public String editGetEvent(@PathVariable(required = false) int eventId,
                                  Model model) {
        Optional<Event> event = eventRepository.findById(eventId);
        Event event1 = event.get();
        model.addAttribute("event",event1);
        return "edit-event";
    }

    @PostMapping({"/edit-event/{eventId}"})
    public String editPostEvent(@PathVariable(required = false) int eventId,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam Integer aantaldeelnemers,
                            Model model) {
      Optional<Event> eventDromDB = eventRepository.findById(eventId);
      if (eventDromDB.isPresent()){
          Event event =eventDromDB.get();
          event.setName(name);
          event.setAantaldeelnemers(aantaldeelnemers);
          event.setDescription(description);
          eventRepository.save(event);
      }

        return "redirect:/";
    }



}

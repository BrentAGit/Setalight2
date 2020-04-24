package be.thomasmore.setalight.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private String application = "Setalight";

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        String loggedInName = principal!=null ? principal.getName() : "nobody";
        model.addAttribute("application", this.application);
        return "home";
    }

    @GetMapping("/admin/test")
    public String testPage(Principal principal) {
        String loggedInName = principal!=null ? principal.getName() : "nobody";
        System.out.println(loggedInName);
        return "/admin/test";
    }

}

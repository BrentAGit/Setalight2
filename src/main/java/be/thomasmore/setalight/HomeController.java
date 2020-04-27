package be.thomasmore.setalight;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private String application = "Setalight";

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("application", this.application);
        return "home";
    }
}

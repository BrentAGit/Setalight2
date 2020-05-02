package be.thomasmore.setalight.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductiehuisController {

    @GetMapping("/page/{ID}")
    public String profilePage(@PathVariable int ID, Model model) {
        return ""
    }
}

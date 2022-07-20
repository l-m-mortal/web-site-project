package com.example.websiteproject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogConroller {

    @GetMapping("/blog")
    public String blogmain(Model model) {
        model.addAttribute("title", "main page");
        return "blogMain";
    }

}

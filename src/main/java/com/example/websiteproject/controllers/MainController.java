package com.example.websiteproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("title", "Main Page");
        return "homepage";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "about";
    }

}

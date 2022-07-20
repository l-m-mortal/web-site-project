package com.example.websiteproject.controllers;

import com.example.websiteproject.models.Posts;
import com.example.websiteproject.repository.IPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private IPostsRepository iPostsRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Posts> posts = iPostsRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model){
        Posts posts = new Posts(title, anons, full_text);
        iPostsRepository.save(posts);
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if(!iPostsRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Posts> posts = iPostsRepository.findById(id);
        ArrayList<Posts> result = new ArrayList<>();
        posts.ifPresent(result::add);
        model.addAttribute("posts", result);
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if(!iPostsRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Posts> posts = iPostsRepository.findById(id);
        ArrayList<Posts> result = new ArrayList<>();
        posts.ifPresent(result::add);
        model.addAttribute("posts", result);
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model){
        Posts posts = iPostsRepository.findById(id).orElseThrow();
        posts.setTitle(title);
        posts.setAnons(anons);
        posts.setFull_text(full_text);
        iPostsRepository.save(posts);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model){
        Posts posts = iPostsRepository.findById(id).orElseThrow();
        iPostsRepository.delete(posts);
        return "redirect:/blog";
    }
}

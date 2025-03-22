package com.devops.controller;

import entity.FormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {


    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
    @GetMapping("/form")
    public String showForm(Model model) {
        // Add the form object to the model so it can be accessed in Thymeleaf
        model.addAttribute("formData", new FormData());
        return "index"; // the name of your Thymeleaf template
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute FormData formData, Model model) {
        // Here you can access the submitted data
        System.out.println("Name: " + formData.getName());
        System.out.println("Email: " + formData.getEmail());
        System.out.println("Message: " + formData.getMessage());
        return "index"; // you can redirect to another page or return the form view again
    }
}

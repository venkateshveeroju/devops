package com.devops.controller;

import com.devops.entity.User;
import com.devops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.IntStream;

@Controller
public class StudentController {


    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // Return the login form page (GET request)
        model.addAttribute("user", new User());
        return "login"; // Thymeleaf view name for login page
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model) {
        // Check if the username and password are correct
        User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            return "redirect:/index";  // Redirect to home page on successful login
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";  // Stay on the login page if authentication fails
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());  // Pre-populate the form with an empty user object
        return "registration";  // Render registration.html
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user, String confirmPassword, Model model) {
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "registration";  // Stay on the registration page with an error
        }

        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            return "redirect:/login";  // Redirect to login page on successful registration
        } else {
            model.addAttribute("error", "User already exists");
            return "registration";  // Stay on the registration page with an error
        }
    }

    @GetMapping("/test")
    public void test() {
        int array[] = {1, 2, 3, 4, 5, 6, 7, 8};
        IntStream.range(0, array.length).filter(i -> i % 2 == 0).map(i -> array[i]).forEach(System.out::println);

    }
}

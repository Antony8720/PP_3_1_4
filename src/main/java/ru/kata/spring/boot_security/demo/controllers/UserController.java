package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.listAll());
        return "all-users";
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        model.addAttribute("user",userService.getUserByUsername(principal.getName()));
        return "show-user";
    }

    @GetMapping("/admin/add")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());

        return "add-user";
    }

    @PostMapping("/admin/add")
    public String createUser(@ModelAttribute("user") User user) {
        System.out.println(user.getUsername());
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update")
    public String updateUser(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.get(id));
        return "update-user";
    }

    @PostMapping("/admin/update")
    public String updateUserPost(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}

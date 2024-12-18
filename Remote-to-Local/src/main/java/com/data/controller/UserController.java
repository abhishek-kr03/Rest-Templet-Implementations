package com.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/sync-users")
    public String syncUsers() {
        userService.fetchAndStoreUsers();
        return "User data synchronized successfully!";
    }
}

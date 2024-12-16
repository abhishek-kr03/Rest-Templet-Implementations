package com.restt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restt.client.UserRestClient;
import com.restt.entity.User;

@Service
public class TestService {

    @Autowired
    private UserRestClient userRestClient;

    public void testRestTemplate() {
        // Get all users
        User[] users = userRestClient.getUsers();
        for (User user : users) {
            System.out.println(user.getFirstName() + " " + user.getLastName());
        }

        // Create a new user
        User newUser = new User("Alice", "Cooper", "alice.cooper@example.com", 36);
        userRestClient.createUser(newUser);

        // Update a user
        User updatedUser = new User("Alice", "Johnson", "alice.johnson@example.com", 37);
        userRestClient.updateUser(1L, updatedUser);

        // Delete a user
        userRestClient.deleteUser(2L);
    }
}

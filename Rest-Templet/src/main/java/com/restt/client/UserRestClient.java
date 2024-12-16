package com.restt.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.restt.entity.User;

@Component
public class UserRestClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/api/users";

    public User[] getUsers() {
        return restTemplate.getForObject(BASE_URL, User[].class);
    }

    public User getUserById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, User.class);
    }

    public User createUser(User user) {
        return restTemplate.postForObject(BASE_URL, user, User.class);
    }

    public void updateUser(Long id, User user) {
        restTemplate.put(BASE_URL + "/" + id, user);
    }

    public void deleteUser(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}

package com.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.data.model.ApiResponse;
import com.data.model.User;
import com.data.repository.UserRepository;

@Service
public class UserService {

    private static final String API_URL = "https://reqres.in/api/users?page=";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void fetchAndStoreUsers() {
        int page = 1;
        int totalPages;

        do {
            ApiResponse apiResponse = restTemplate.getForObject(API_URL + page, ApiResponse.class);
            if (apiResponse != null) {
                totalPages = apiResponse.getTotal_pages();
                List<User> users = apiResponse.getData();

                userRepository.saveAll(users);
                page++;
            } else {
                break;
            }
        } while (page <= totalPages);
    }
}

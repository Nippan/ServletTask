package org.clientSpringBoot.service;

import org.clientSpringBoot.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestClient {
    private static final String GET_ADMIN_PAGE = "http://localhost:8080/admin/";
    private static final String GET_ADMIN_URL = "http://localhost:8080/admin/users";
    private RestTemplate restTemplate = new RestTemplate();

    public List<User> getAllUsers() {
        return Arrays.stream(restTemplate.getForObject(GET_ADMIN_URL, User[].class)).collect(Collectors.toList());
    }

    public User getUserByName(String name) {
        return restTemplate.getForObject(GET_ADMIN_PAGE + name, User.class);
    }

    //create user
    public User addUser(User user) {
        return restTemplate.postForObject(GET_ADMIN_PAGE, user, User.class);
    }

    //delete user
    public void delete(Long id){
        restTemplate.delete(GET_ADMIN_PAGE+id);
    }

    //update user
    public User update(Long id, User user){
        return restTemplate.exchange(GET_ADMIN_PAGE+id, HttpMethod.PUT,
                new HttpEntity<>(user), User.class, id).getBody();

    }

}

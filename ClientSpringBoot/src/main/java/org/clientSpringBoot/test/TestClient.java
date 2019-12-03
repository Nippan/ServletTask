package org.clientSpringBoot.test;

import org.clientSpringBoot.model.User;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TestClient {
    private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/admin";
    private static final String GET_USER_BY_ID = "http://localhost:8080/admin/{id}";
    private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/admin";
    private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/admin/{id}";
    private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/admin/{id}";
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        TestClient springRestClient = new TestClient();
        springRestClient.getUserById();
//        springRestClient.createUser();
//        springRestClient.getUsers();
//        springRestClient.updateUser();
//        springRestClient.deleteUser();
    }


    private void getUserById() {
        Map< String, String > params = new HashMap< String, String >();
        params.put("id", "2");

        RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.getForObject(GET_USER_BY_ID, User.class, params);
        System.out.println(result);
    }

    private void createUser() {

        User newUser = new User("Roland", "admin@gmail.com", "333");

        RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, newUser, User.class);
        System.out.println(result);
    }

    private void updateUser() {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "1");
        User updateUser = new User("Aslanbek", "admin123@gmail.com", "33322");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updateUser, params);
    }

    private void deleteUser() {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "13");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
    }
}
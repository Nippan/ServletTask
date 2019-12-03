package org.clientSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private RestClient restClient;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return restClient.getUserByName(s);
    }
}

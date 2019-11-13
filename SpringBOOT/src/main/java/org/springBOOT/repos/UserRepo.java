package org.springBOOT.repos;

import org.springBOOT.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

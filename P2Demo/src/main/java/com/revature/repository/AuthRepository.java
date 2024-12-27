package com.revature.repository;

import com.revature.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Integer> {


    User findByUsernameAndPassword(String username, String password);
}

package com.revature.services;

import com.revature.controller.AuthController;
import com.revature.model.User;
import com.revature.model.dto.LoginDTO;
import com.revature.model.dto.OutgoingUserDTO;
import com.revature.repository.AuthRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    // Method that takes in username/password and return a user if found
    // AKA user login method
    public OutgoingUserDTO login(LoginDTO loginDTO) {
        User user = authRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        // If no user is found throw an exception;
        if (user == null) throw new IllegalArgumentException("Invalid username and password");

        return new OutgoingUserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole(),
                user.getTeam()
        );
    }
}

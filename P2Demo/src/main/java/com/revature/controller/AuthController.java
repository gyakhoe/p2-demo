package com.revature.controller;

import com.revature.model.dto.LoginDTO;
import com.revature.model.dto.OutgoingUserDTO;
import com.revature.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<OutgoingUserDTO> login(@RequestBody LoginDTO loginDTO, HttpSession session) {


        // Note: we have incoming session coming from request;
        // This is different from incoming session
        // We are going to help initialize the session


        // send the loginDTO to the service
        OutgoingUserDTO userDto  = authService.login(loginDTO);

        // the session get initialized
        // Store user in session
        session.setAttribute("userId", userDto.getUserId());
        session.setAttribute("username", userDto.getUsername());
        session.setAttribute("role", userDto.getRole());
        return ResponseEntity.ok().body(userDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}

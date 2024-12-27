package com.revature.controller;

import com.revature.aspect.AdminOnly;
import com.revature.model.User;
import com.revature.model.dto.IncomingUserDTO;
import com.revature.model.dto.OutgoingUserDTO;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Combines @Controller, and @ResponseBody
@RequestMapping("/users") // All request after /users will be sent to this controller.
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody IncomingUserDTO userDTO) {
        User user = userService.isnertUser(userDTO);
        return ResponseEntity.ok().body(user);
    }

    // A method that update a User's password (Just one column, so it's a PATcH)
    @PatchMapping("/password/{userId}")
    public ResponseEntity<User> updateUserPassword(@PathVariable int userId, @RequestBody String password) {
        // oneliner - send back a 202 (accepted) with the updated user
        return ResponseEntity.accepted().body(
                userService.updateUserPassword(userId, password)
        );
    }

    @GetMapping
    @AdminOnly
    public ResponseEntity<List<OutgoingUserDTO>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> exceptionHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}

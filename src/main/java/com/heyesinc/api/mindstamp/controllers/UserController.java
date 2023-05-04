package com.heyesinc.api.mindstamp.controllers;

import com.heyesinc.api.mindstamp.dtos.User;
import com.heyesinc.api.mindstamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int userId){
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userId){
        return ResponseEntity.ok().body(userService.deleteUserById(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUserById(@PathVariable("id") Integer userId, @RequestBody User user){
        return ResponseEntity.ok().body(userService.editUserById(userId, user));
    }

}

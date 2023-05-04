package com.heyesinc.api.mindstamp.controller;

import com.heyesinc.api.mindstamp.dtos.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int userId){
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userId){
        return userService.deleteUserById(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUserById(@RequestBody User user, @PathVariable("id") Integer userId){
        return userService.editUserById(user, userId);
    }

}

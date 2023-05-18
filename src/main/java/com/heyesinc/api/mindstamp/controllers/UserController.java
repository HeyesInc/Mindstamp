package com.heyesinc.api.mindstamp.controllers;

import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;
import com.heyesinc.api.mindstamp.dtos.User;
import com.heyesinc.api.mindstamp.dtos.UserRequest;
import com.heyesinc.api.mindstamp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@CrossOrigin
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
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId){
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @PostMapping("/posts")
    public ResponseEntity<String> addPostToUser(@RequestBody PostRequest postRequest,
                                                HttpServletRequest token){
        return ResponseEntity.ok().body(userService.addPostToUser(postRequest,token));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getUserPostsByToken(HttpServletRequest token){
        return ResponseEntity.ok().body(userService.getUserPostsByToken(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userId){
        return ResponseEntity.ok().body(userService.deleteUserById(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editUserById(@PathVariable("id") Integer userId,
                                               @RequestBody UserRequest newUser){
        return ResponseEntity.ok().body(userService.editUserById(userId, newUser));
    }

}

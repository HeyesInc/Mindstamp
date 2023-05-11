package com.heyesinc.api.mindstamp.services.impl;

import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;
import com.heyesinc.api.mindstamp.dtos.User;
import com.heyesinc.api.mindstamp.dtos.UserRequest;
import com.heyesinc.api.mindstamp.repositorys.UserRepository;
import com.heyesinc.api.mindstamp.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public String addUser(UserRequest userRequest) {
         userRepository.save(User.builder()
                         .username(userRequest.getUsername())
                         .password(userRequest.getPassword())
                         .posts(null)
                         .build());
         return "User Created";
    }

    @Override
    @Transactional
    public String addPostToUser(int userId, PostRequest postRequest) {
        User user = userRepository.findById(userId).orElseThrow();
        Post post = Post.builder()
                .content(postRequest.getContent())
                .user(user)
                .build();
        user.getPosts().add(post);
        return "Post added";
    }

    @Override
    @Transactional
    public String editUserById(int userId, UserRequest newUser) {
        User user = userRepository.findById(userId).orElseThrow();
        if(newUser.getUsername() != null){
            user.setPassword(newUser.getPassword());
        }
        if(newUser.getPassword() != null){
            user.setUsername(newUser.getUsername());
        }
        return "User updated";
    }

    @Override
    @Transactional
    public String deleteUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);
        return "User deleted";
    }
}

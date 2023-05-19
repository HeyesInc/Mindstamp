package com.heyesinc.api.mindstamp.services.impl;

import com.heyesinc.api.mindstamp.authentication.JwtService;
import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;
import com.heyesinc.api.mindstamp.dtos.User;
import com.heyesinc.api.mindstamp.dtos.UserRequest;
import com.heyesinc.api.mindstamp.repositorys.UserRepository;
import com.heyesinc.api.mindstamp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
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
    public List<Post> getUserPostsByToken(HttpServletRequest token) {
        User user=  userRepository.findByUsername(jwtService.emailFromJwt(token)).orElseThrow();
        return user.getPosts();
    }

    @Override
    @Transactional
    public String addPostToUser(PostRequest postRequest, HttpServletRequest token) {
        User user = userRepository.findByUsername(jwtService.emailFromJwt(token)).orElseThrow();
        Post post = Post.builder()
                .content(postRequest.getContent())
                .user(user)
                .username(user.getUsername())
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

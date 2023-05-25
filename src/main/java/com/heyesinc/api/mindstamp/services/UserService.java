package com.heyesinc.api.mindstamp.services;

import com.heyesinc.api.mindstamp.dtos.Post;
import com.heyesinc.api.mindstamp.dtos.PostRequest;
import com.heyesinc.api.mindstamp.dtos.User;
import com.heyesinc.api.mindstamp.dtos.UserRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {

    List<User> getAllUsers(HttpServletRequest request);

    User getUserById(int userId);

    List<Post> getUserPostsByToken(HttpServletRequest token);

    String addPostToUser(PostRequest postRequest, HttpServletRequest token);

    String editUserById(int userId, UserRequest user);

    String deleteUserById(int userId);

}

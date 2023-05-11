package com.heyesinc.api.mindstamp.services;

import com.heyesinc.api.mindstamp.dtos.User;
import com.heyesinc.api.mindstamp.dtos.UserRequest;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(int userId);

    public String addUser(UserRequest userRequest);

    public String editUserById(int userId, UserRequest user);

    public String deleteUserById(int userId);

}

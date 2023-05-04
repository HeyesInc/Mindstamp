package com.heyesinc.api.mindstamp.services;

import com.heyesinc.api.mindstamp.dtos.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(int Id);

    public User editUserById(int Id, User user);

    public String deleteUserById(int Id);

}

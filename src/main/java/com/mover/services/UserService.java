package com.mover.services;

import com.mover.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long userId, UserDto userDto);
    UserDto getUserById(Long userId);
    UserDto getUserByEmail(String emailId);
    List<UserDto> getAllUsers();
    void deleteUser(Long userId);
}

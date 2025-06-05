package com.mover.services;

import com.mover.payloads.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long userId, UserDto userDto);
    UserDto getUserById(Long userId);
    UserDto getUserByEmail(Long emailId);
    void deleteUser(Long userId);
}

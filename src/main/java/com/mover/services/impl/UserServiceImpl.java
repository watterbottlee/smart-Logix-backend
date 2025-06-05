package com.mover.services.impl;

import com.mover.entities.User;
import com.mover.exceptions.ResourceNotFoundException;
import com.mover.payloads.UserDto;
import com.mover.repositories.UserRepository;
import com.mover.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.DtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(Long emailId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId){

    }
    public UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
    public User DtoToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}

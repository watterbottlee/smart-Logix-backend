package com.mover.services.impl;

import com.mover.entities.User;
import com.mover.exceptions.ResourceNotFoundException;
import com.mover.payloads.UserDto;
import com.mover.repositories.UserRepository;
import com.mover.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }


    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        modelMapper.map(userDto, user);

        User updatedUser = userRepo.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return modelMapper.map(user, UserDto.class);
    }

   @Override
   public UserDto getUserByEmail(String emailId) {
       User user = userRepo.findByEmail(emailId)
               .orElseThrow(() -> new ResourceNotFoundException("User", "email", emailId
               ));
       return modelMapper.map(user, UserDto.class);

   }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepo.delete(user);
    }

}

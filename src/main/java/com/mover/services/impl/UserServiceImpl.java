package com.mover.services.impl;

import com.mover.entities.User;
import com.mover.exceptions.ResourceNotFoundException;
import com.mover.payloads.UserDto;
import com.mover.repositories.UserRepository;
import com.mover.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.DtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }


    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","user_id",String.valueOf(userId)));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }


    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","user_id",String.valueOf(userId)));
        return this.userToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String emailId) {
        User user = this.userRepo.findByEmail(emailId)
                .orElseThrow(()->new ResourceNotFoundException("user","email_id",emailId));
        return this.userToDto(user);
    }

    @Override
    public void deleteUser(Long userId){
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","email_id",String.valueOf(userId)));
        this.userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> allUsers = this.userRepo.findAll();
        List<UserDto> allUserDtos = allUsers.stream().map(this::userToDto).toList();
        return allUserDtos;
    }
    public UserDto userToDto(User user){
        return this.modelMapper.map(user, UserDto.class);
    }
    public User DtoToUser(UserDto userDto){
        return this.modelMapper.map(userDto,User.class);
    }
}

package com.mover.controllers;

import com.mover.exceptions.DeleteResponse;
import com.mover.payloads.UserDto;
import com.mover.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("update-user/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @Valid @RequestBody UserDto userDto, @PathVariable("userId") Long userId){
        UserDto updatedUser = this.userService.updateUser(userId,userDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @GetMapping("getallusers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUserDtos = this.userService.getAllUsers();
        return new ResponseEntity<>(allUserDtos,HttpStatus.OK);
    }

    @GetMapping("getuserbyid/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("getuserbyemail/{emailId}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("emailId") String emailId){
        UserDto userDto = this.userService.getUserByEmail(emailId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(
                new DeleteResponse("user deleted successfully",true),HttpStatus.OK);
    }
}

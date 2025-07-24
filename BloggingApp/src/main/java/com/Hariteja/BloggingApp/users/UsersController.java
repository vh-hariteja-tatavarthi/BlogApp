package com.Hariteja.BloggingApp.users;

import com.Hariteja.BloggingApp.users.dto.CreateUserRequest;
import com.Hariteja.BloggingApp.users.dto.LoginRequest;
import com.Hariteja.BloggingApp.users.dto.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")

public class UsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;


    public UsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper=modelMapper;
    }

    @GetMapping("")
    String getUsers(){
        return "Users";
    }

    @PostMapping("")
    ResponseEntity<UserResponse> signup(@RequestBody CreateUserRequest request){
        UserEntity savedUser= userService.createUser(request);
        URI savedUserURI = URI.create("/users/"+ savedUser.getId());
        return ResponseEntity.created(savedUserURI).body(modelMapper.map(savedUser, UserResponse.class));
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> login(@RequestBody LoginRequest request){
        UserEntity savedUser= userService.loginUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok().body(modelMapper.map(savedUser,UserResponse.class));
    }
}

package com.Hariteja.BloggingApp.users;

import com.Hariteja.BloggingApp.Security.JWTService;
import com.Hariteja.BloggingApp.common.dto.ExceptionResponse;
import com.Hariteja.BloggingApp.users.dto.CreateUserRequest;
import com.Hariteja.BloggingApp.users.dto.LoginRequest;
import com.Hariteja.BloggingApp.users.dto.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;


import java.net.URI;

@RestController
@RequestMapping("/users")

public class UsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;


    public UsersController(UserService userService, ModelMapper modelMapper,JWTService jwtService) {
        this.userService = userService;
        this.modelMapper=modelMapper;
        this.jwtService=jwtService;
    }



    @GetMapping("")
    String getUsers(){
        return "Users";
    }

    @PostMapping("/signup")
    ResponseEntity<UserResponse> signup(@RequestBody CreateUserRequest request){
        UserEntity savedUser= userService.createUser(request);
        URI savedUserURI = URI.create("/users/"+ savedUser.getId());
        var userResponse = modelMapper.map(savedUser,UserResponse.class);
        userResponse.setJwtToken(jwtService.createJWT(savedUser.getId()));
        return ResponseEntity.created(savedUserURI).body(userResponse);
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> login(@RequestBody LoginRequest request){
        UserEntity savedUser= userService.loginUser(request.getUsername(), request.getPassword());
        var userResponse = modelMapper.map(savedUser,UserResponse.class);
        userResponse.setJwtToken(jwtService.createJWT(savedUser.getId()));
        return ResponseEntity.ok().body(userResponse);
    }

    @ExceptionHandler({UserService.UserNotFoundException.class})
     ResponseEntity<ExceptionResponse> handlesUserException(Exception ex){
        String message;
        HttpStatus status;

        if(ex instanceof UserService.UserNotFoundException){
            message= ex.getMessage();
            status=HttpStatus.NOT_FOUND;
        }
        else if(ex instanceof UserService.InvalidCredentialException){
            message=ex.getMessage();
            status=HttpStatus.UNAUTHORIZED;
        }
        else{
            message= "something went wrong";
            status= HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ExceptionResponse response= ExceptionResponse.builder()
                        .message(message)
                        .build();

        return ResponseEntity.notFound().build();

    }
}

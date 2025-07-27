package com.Hariteja.BloggingApp.users;

import com.Hariteja.BloggingApp.Security.JWTService;
import com.Hariteja.BloggingApp.users.dto.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    JWTService jwtService;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
        this.passwordEncoder=passwordEncoder;
    }

    public UserEntity createUser(CreateUserRequest createUserRequest){

        UserEntity newUser= modelMapper.map(createUserRequest,UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
//
//            var newUser= UserEntity.builder()
//                    .username(createUserRequest.getUsername())
//                  //  .password(createUserRequest.getPassword())
//                    .email(createUserRequest.getEmail())
//                    .build();
            return userRepository.save(newUser);

    }
    
    public UserEntity getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
    public UserEntity getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public UserEntity loginUser(String username, String password){
        var user= userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        var passMatch= passwordEncoder.matches(password,user.getPassword());
        if(!passMatch) throw new InvalidCredentialException();
//        System.out.println(jwtService.createJWT(user.getId()));
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username){
            super("User with username"+ username+" Not Found");
        }
        public UserNotFoundException(Long userid){
            super("User with id:"+ userid + "Not found");
        }
    }
    public static class InvalidCredentialException extends IllegalArgumentException{
        public InvalidCredentialException(){
            super("username and password doesn't match");
        }
    }
}

package com.Hariteja.BloggingApp.users;

import com.Hariteja.BloggingApp.users.dto.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    public UserRepository userRepository;
    UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public UserEntity createUser(CreateUserRequest createUserRequest){

            var newUser= UserEntity.builder()
                    .username(createUserRequest.getUsername())
                  //  .password(createUserRequest.getPassword())
                    .email(createUserRequest.getEmail())
                    .build();
            return userRepository.save(newUser);

    }
    
    public UserEntity getUserByUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
    public UserEntity getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public UserEntity loginUser(String username, String Password){
        var user= userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        //Check the Password

        return user;
    }
    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username){
            super("User"+ username+" Not Found");
        }
        public UserNotFoundException(Long userid){
            super("User with id:"+ userid + "Not found");
        }
    }
}

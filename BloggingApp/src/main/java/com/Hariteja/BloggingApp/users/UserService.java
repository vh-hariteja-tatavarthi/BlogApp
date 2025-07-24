package com.Hariteja.BloggingApp.users;

import com.Hariteja.BloggingApp.users.dto.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }

    public UserEntity createUser(CreateUserRequest createUserRequest){

        UserEntity newUser= modelMapper.map(createUserRequest,UserEntity.class);
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

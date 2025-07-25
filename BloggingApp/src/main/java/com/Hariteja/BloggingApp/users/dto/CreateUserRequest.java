package com.Hariteja.BloggingApp.users.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Data
@Setter(AccessLevel.NONE)

public class CreateUserRequest {
    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String email;

}

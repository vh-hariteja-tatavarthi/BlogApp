package com.Hariteja.BloggingApp.users.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Data
@Setter(AccessLevel.NONE)

public class LoginRequest {
    @NonNull
    private String username;

    @NonNull
    private String password;

}

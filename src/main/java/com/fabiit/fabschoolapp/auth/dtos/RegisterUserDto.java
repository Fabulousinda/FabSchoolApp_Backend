package com.fabiit.fabschoolapp.auth.dtos;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String fullName;
    
}
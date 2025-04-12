package com.erp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {
    private String email;

    private String password;

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    // getters and setters here...
}

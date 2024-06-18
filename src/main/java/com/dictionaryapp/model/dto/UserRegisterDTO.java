package com.dictionaryapp.model.dto;

import com.dictionaryapp.validation.annotation.PasswordMatcher;
import com.dictionaryapp.validation.annotation.UniqueEmail;
import com.dictionaryapp.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatcher
public record UserRegisterDTO(

        @NotBlank
        @Size(min = 3, max = 20, message = "{user.username.length}")
        @UniqueUsername
        String username,

        @NotBlank
        @Email(message = "{user.email}")
        @UniqueEmail
        String email,

        @NotBlank
        @Size(min = 3, max = 20, message = "{user.password.length}")
        String password,

        @NotBlank
        @Size(min = 3, max = 20, message = "{user.confirm-password.length}")
        String confirmPassword) {
}

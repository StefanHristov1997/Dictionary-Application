package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDTO {

    @NotBlank(message = "{not.blank}")
    @Size(min = 3, max = 20, message = "{user.username.length}")
    private String username;


    @NotBlank(message = "{not.blank}")
    @Size(min = 3, max = 20, message = "{user.username.length}")
    private String password;
}

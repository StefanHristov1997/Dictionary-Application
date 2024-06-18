package com.dictionaryapp.service;


import com.dictionaryapp.model.dto.LoginUserDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.UserEntity;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO);

    boolean loginUser(LoginUserDTO loginUserDTO);
    void logoutUser();
}

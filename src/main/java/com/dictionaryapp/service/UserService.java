package com.dictionaryapp.service;


import com.dictionaryapp.model.dto.UserRegisterDTO;

public interface UserService {

    void registerUser(UserRegisterDTO user);
}

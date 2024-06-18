package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.repository.UserRepository;
import com.dictionaryapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {

        UserEntity userToSave = mapper.map(userRegisterDTO, UserEntity.class);

        this.userRepository.save(userToSave);
    }
}

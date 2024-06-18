package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.LoginUserDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.repository.UserRepository;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.CurrentUserSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUserSession currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, PasswordEncoder passwordEncoder, CurrentUserSession currentUser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {

        UserEntity userToSave = mapper.map(userRegisterDTO, UserEntity.class);

        this.userRepository.save(userToSave);
    }

    @Override
    public boolean loginUser(LoginUserDTO loginUserDTO) {

        Optional<UserEntity> user = userRepository
                .findByUsername(loginUserDTO.getUsername());

        if (user.isPresent() && passwordEncoder.matches(loginUserDTO.getPassword(), user.get().getPassword())) {
            currentUser.logUser(user.get());
            return true;
        }

        return false;
    }
}

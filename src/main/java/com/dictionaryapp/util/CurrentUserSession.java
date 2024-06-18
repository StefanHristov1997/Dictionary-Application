package com.dictionaryapp.util;

import com.dictionaryapp.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@Component
@SessionScope
public class CurrentUserSession {

    private Long id;

    private String username;

    private boolean isLogged = false;

    public void logUser(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.isLogged = true;
    }

}

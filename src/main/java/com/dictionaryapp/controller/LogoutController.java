package com.dictionaryapp.controller;

import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.CurrentUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LogoutController {

    private final UserService userService;
    private final CurrentUserSession currentUserSession;

    @Autowired
    public LogoutController(UserService userService, CurrentUserSession currentUserSession) {
        this.userService = userService;
        this.currentUserSession = currentUserSession;
    }

    @GetMapping("/logout")
    public String logout() {

        if (!currentUserSession.isLogged()) {
            return "redirect:/users/login";
        }

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String doLogout() {

        if (!currentUserSession.isLogged()) {
            return "redirect:/users/login";
        }

        userService.logoutUser();
        return "redirect:/";
    }
}

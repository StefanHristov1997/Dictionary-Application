package com.dictionaryapp.controller;

import com.dictionaryapp.util.CurrentUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUserSession  currentUserSession;

    @Autowired
    public HomeController(CurrentUserSession currentUserSession) {
        this.currentUserSession = currentUserSession;
    }

    @GetMapping("/")
    public String viewIndex() {

        if(currentUserSession.isLogged()){
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String viewHome() {

        if(!currentUserSession.isLogged()){
            return "redirect:/";
        }

        return "home";
    }
}

package com.dictionaryapp.controller;

import com.dictionaryapp.util.CurrentUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WordController {

    private final CurrentUserSession currentUserSession;

    @Autowired
    public WordController(CurrentUserSession currentUserSession) {
        this.currentUserSession = currentUserSession;
    }

    @GetMapping("/word-add")
    public String viewAddWord() {

        if(!currentUserSession.isLogged()){
            return "redirect:/users/login";
        }

        return "word-add";
    }
}

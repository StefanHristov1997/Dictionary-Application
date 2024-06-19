package com.dictionaryapp.controller;

import com.dictionaryapp.model.enums.LanguageNamesEnum;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final WordService wordService;

    private final CurrentUserSession  currentUserSession;

    @Autowired
    public HomeController(WordService wordService, CurrentUserSession currentUserSession) {
        this.wordService = wordService;
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
    public String viewHome(Model model) {

        if(!currentUserSession.isLogged()){
            return "redirect:/";
        }

        model.addAttribute("french", wordService.findWordsByLanguageName(LanguageNamesEnum.FRENCH));
        model.addAttribute("german", wordService.findWordsByLanguageName(LanguageNamesEnum.GERMAN));
        model.addAttribute("italian", wordService.findWordsByLanguageName(LanguageNamesEnum.ITALIAN));
        model.addAttribute("spanish", wordService.findWordsByLanguageName(LanguageNamesEnum.SPANISH));

        return "home";
    }
}

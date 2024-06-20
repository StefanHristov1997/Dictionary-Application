package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enums.LanguageNamesEnum;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

        final List<WordEntity> frenchWords =  wordService.findWordsByLanguageName(LanguageNamesEnum.FRENCH);
        final List<WordEntity> germanWords =   wordService.findWordsByLanguageName(LanguageNamesEnum.GERMAN);
        final List<WordEntity> spanishWords =  wordService.findWordsByLanguageName(LanguageNamesEnum.SPANISH);
        final List<WordEntity> italianWords =  wordService.findWordsByLanguageName(LanguageNamesEnum.ITALIAN);

        int allWords = frenchWords.size() + germanWords.size() + spanishWords.size() + italianWords.size();

        model.addAttribute("french", frenchWords);
        model.addAttribute("german", germanWords);
        model.addAttribute("italian", spanishWords);
        model.addAttribute("spanish", italianWords);
        model.addAttribute("allWords", allWords);

        return "home";
    }
}

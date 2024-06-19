package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUserSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private WordService wordService;

    private final CurrentUserSession currentUserSession;

    @Value("${binding-result-package}")
    private String bindingResultPackage;

    private final String attribute = "addWordDTO";

    @Autowired
    public WordController(WordService wordService, CurrentUserSession currentUserSession) {
        this.wordService = wordService;
        this.currentUserSession = currentUserSession;
    }

    @GetMapping("/word-add")
    public String viewAddWord(Model model) {

        if(!currentUserSession.isLogged()){
            return "redirect:/";
        }

        if(!model.containsAttribute(attribute)){
            model.addAttribute(attribute, new AddWordDTO());
        }

        return "word-add";
    }

    @PostMapping("/word-add")
    public String doAddWord(
            @Valid AddWordDTO addWordDTO,
            BindingResult bindingResult,
            RedirectAttributes rAtt)
    {

        if(!currentUserSession.isLogged()){
            return "redirect:/";
        }

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute(attribute, addWordDTO);
            rAtt.addFlashAttribute(bindingResultPackage + "." + attribute, bindingResult);
            return "redirect:/word-add";
        }

        wordService.addWord(addWordDTO);

        return "redirect:/home";
    }
}

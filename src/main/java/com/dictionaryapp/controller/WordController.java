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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
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

    @GetMapping("/add-word")
    public String viewAddWord(Model model) {

        if(!currentUserSession.isLogged()){
            return "redirect:/";
        }

        if(!model.containsAttribute(attribute)){
            model.addAttribute(attribute, new AddWordDTO());
        }

        return "word-add";
    }

    @PostMapping("/add-word")
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
            return "redirect:/words/add-word";
        }

        wordService.addWord(addWordDTO);

        return "redirect:/home";
    }

    @DeleteMapping("remove-word/{id}")
    public String deleteWord(@PathVariable("id") Long id){

        if(!currentUserSession.isLogged()){
            return "redirect:/";
        }

        wordService.removeWord(id);

        return "redirect:/home";
    }

    @DeleteMapping("/remove-all")
    public String deleteAllWords(){

        if(!currentUserSession.isLogged()){
            return "redirect:/";
        }

        wordService.removeAllWords();

        return "redirect:/home";
    }
}

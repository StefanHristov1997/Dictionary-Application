package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.LoginUserDTO;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.CurrentUserSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class LoginController {

    private final UserService userService;

    private final CurrentUserSession currentUserSession;

    @Value("${binding-result-package}")
    private String bindingResultPackage;

    private final String attribute = "loginUserDTO";

    @Autowired
    public LoginController(UserService userService, CurrentUserSession currentUserSession) {
        this.userService = userService;
        this.currentUserSession = currentUserSession;
    }

    @GetMapping("/login")
    public String viewLogin(Model model) {

        if(currentUserSession.isLogged()){
            return "redirect:/home";
        }

        if(!model.containsAttribute(attribute)) {
            model.addAttribute(attribute, new LoginUserDTO());
        }

        return "login";
    }

    @PostMapping("/login")
    public String doLogin(
            @Valid LoginUserDTO loginUserDTO,
            BindingResult bindingResult,
            RedirectAttributes rAtt) {

        if(currentUserSession.isLogged()){
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute(attribute, loginUserDTO);
            rAtt.addFlashAttribute(bindingResultPackage + "." + attribute, bindingResult);
            return "redirect:/users/login";
        }

        boolean success = userService.loginUser(loginUserDTO);

        if(!success) {
            rAtt.addFlashAttribute(attribute, loginUserDTO);
            rAtt.addFlashAttribute("badCredentials", true);
            return "redirect:/users/login";
        }

        return "redirect:/home";
    }
}

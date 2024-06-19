package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.UserRegisterDTO;
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
public class RegisterController {

    private final UserService userService;
    private final CurrentUserSession currentUserSession;

    @Value("${binding-result-package}")
    private String bindingResultPackage;

    private final String attribute = "userRegisterDTO";


    @Autowired
    public RegisterController(UserService userService, CurrentUserSession currentUserSession) {
        this.userService = userService;
        this.currentUserSession = currentUserSession;
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {

        if(currentUserSession.isLogged()){
            return "redirect:/home";
        }

        if (!model.containsAttribute(attribute)) {
            model.addAttribute(attribute, new UserRegisterDTO());
        }

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(
            @Valid UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult,
            RedirectAttributes rAtt) {

        if(currentUserSession.isLogged()){
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute(attribute, userRegisterDTO);
            rAtt.addFlashAttribute(bindingResultPackage + "." + attribute, bindingResult);
            return "redirect:/users/register";
        }

        userService.registerUser(userRegisterDTO);

        return "redirect:/users/login";
    }
}

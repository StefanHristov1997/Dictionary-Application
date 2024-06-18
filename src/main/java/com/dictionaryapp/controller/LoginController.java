package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.LoginUserDTO;
import com.dictionaryapp.service.UserService;
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

    @Value("${binding-result-package}")
    private String bindingResultPackage;

    private final String attribute = "loginUserDTO";

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {

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

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute(attribute, loginUserDTO);
            rAtt.addFlashAttribute(bindingResultPackage + "." + attribute, bindingResult);
            return "redirect:login";
        }

        boolean success = userService.loginUser(loginUserDTO);

        if(!success) {
            rAtt.addFlashAttribute(attribute, loginUserDTO);
            rAtt.addFlashAttribute("badCredentials", true);
            return "redirect:login";
        }

        return "redirect:/home";
    }
}

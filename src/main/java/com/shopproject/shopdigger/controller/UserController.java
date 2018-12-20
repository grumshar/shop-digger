package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.dto.UserDto;
import com.shopproject.shopdigger.security.provider.DatabaseAuthenticationProvider;
import com.shopproject.shopdigger.service.UserService;
import com.shopproject.shopdigger.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private DatabaseAuthenticationProvider databaseAuthenticationProvider;

    @InitBinder("userDto")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }


    @GetMapping("/register")
    public String registerCustomer(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registration";
    }


    @PostMapping("/register")
    public String registerCustomer(@Valid @ModelAttribute("userDto")  UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        userService.registerUser(userDto);
       model.addAttribute("message", messageSource.getMessage("user.registered",new Object[] {userDto.getMail()}, Locale.getDefault() ));
        return "register-message";
    }

    @GetMapping("/customers/activate")
    public String registerCustomer(@RequestParam("login") String login, @RequestParam("token") String token, Model model) {
        boolean activated=userService.activateUser(login, token);

        String message;
        if (activated) {
            message = "customer.activated";
        } else {
            message = "customer.activated.fail";
        }

        model.addAttribute("message", messageSource.getMessage(message,new Object[] {login}, Locale.getDefault() ));

        return "activation-message";
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String customerLogin() {
        return "login";
    }

    @GetMapping("/shouldBeLogged")
    public String customerLogin(Model model, Authentication authentication) {
        model.addAttribute("message", "User "+authentication.getName()+" is logged");
        return "succesfull-login";
    }
}

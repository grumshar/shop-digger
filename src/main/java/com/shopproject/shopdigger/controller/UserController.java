package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.converters.UserConverterImpl;
import com.shopproject.shopdigger.dto.UserDto;
import com.shopproject.shopdigger.model.Address;
import com.shopproject.shopdigger.model.User;
import com.shopproject.shopdigger.security.provider.DatabaseAuthenticationProvider;
import com.shopproject.shopdigger.service.AddressService;
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
    private AddressService addressService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserConverterImpl userConverter;

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

    @GetMapping("/logout")
    public String customerLogout() {
        return "redirect:/succesfull-login";
    }

    @GetMapping("/logout-done")
    public String customerLogoutDone() {
        return "redirect:/index";
    }


    @GetMapping("/shouldBeLogged")
    public String customerLogin(Model model, Authentication authentication) {
        model.addAttribute("message", "User "+authentication.getName()+" is logged");
        return "redirect:/index";
    }


    @GetMapping("/user-profile")
    public String userProfile(Model model,Authentication authentication) {

        UserDto userDto = (UserDto) authentication.getPrincipal();

         model.addAttribute("profile",userDto);


        return "user-profile";
    }

    @PostMapping("/save-profile")
    public String userProfileChange(@RequestParam Long id,@ModelAttribute UserDto userDto,Model model) {

        User user = userService.findById(id);
        user.setFirstName(userDto.getFirstName());
        user.setSecondName(userDto.getSecondName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        model.addAttribute("profile",userDto);
        System.out.println(userDto);

        userService.saveUser(user);


        return "user-profile";
    }

    @GetMapping("/user-address")
    public String userAddress(Model model,Authentication authentication) {



        UserDto userDto = (UserDto) authentication.getPrincipal();
        Address address = userDto.getAddress();

        model.addAttribute("address",userDto.getAddress());


        return "user-address";
    }

    @PostMapping("/save-address")
    public String userAddressChange(@ModelAttribute Address address,Authentication authentication) {

    UserDto userDto =(UserDto) authentication.getPrincipal();
    addressService.saveAdress(address);
    Address address1 = addressService.findById(address.getId());
    userDto.setAddress(address1);


        return "user-address";
    }
}

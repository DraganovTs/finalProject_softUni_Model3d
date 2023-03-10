package com.homecode.customer.web;

import com.homecode.library.model.dto.UserRegisterDTO;
import com.homecode.library.service.impl.CustomerUserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final CustomerUserServiceImpl customerUserService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomerUserServiceImpl customerUserService, PasswordEncoder passwordEncoder) {
        this.customerUserService = customerUserService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "user-login";
    }

    @PostMapping("/login-error")
    public String postLogin() {
        return "error";
    }

    @GetMapping("/register")
    public String register() {
        return "user-register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterDTO registerDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {


        try {
            if (!customerUserService.confirmPassword(registerDTO)) {
                bindingResult.addError(
                        new FieldError(
                                "differentConfirmPassword",
                                "repeatPassword",
                                "Passwords must be the same."));

                System.out.println("Passwords must be the same.");

            }

            registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

            if (bindingResult.hasErrors() || !this.customerUserService.register(registerDTO)) {
                redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
                System.out.println("Errors");
                return "redirect:/register";
            }

        } catch (Exception e) {
            System.out.println("server error");
            redirectAttributes.addAttribute("errors", "The server has been down!");
        }


        return "redirect:/login";
    }


    @ModelAttribute("registerDTO")
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }
}

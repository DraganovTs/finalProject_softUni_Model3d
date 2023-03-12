package com.homecode.admin.web;

import com.homecode.library.model.dto.AdminRegisterDTO;
import com.homecode.library.service.impl.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminAuthController {

    private final AdminServiceImpl adminService;
    private final PasswordEncoder passwordEncoder;


    public AdminAuthController(AdminServiceImpl adminService, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "/user-login";
    }

//    @GetMapping("/register")
//    public String register() {
//        return "/user-register";
//    }

//    @PostMapping("/register")
//    public String adminRegister(@Valid AdminRegisterDTO adminRegisterDTO,
//                                BindingResult bindingResult,
//                                RedirectAttributes redirectAttributes) {
//
//        try {
//            if (!this.adminService.confirmPassword(adminRegisterDTO)) {
//                bindingResult.addError(
//                        new FieldError(
//                                "differentConfirmPassword",
//                                "confirmPassword",
//                                "Passwords must be the same."));
//                return "redirect:/register";
//            }
//
//            adminRegisterDTO.setPassword(passwordEncoder.encode(adminRegisterDTO.getPassword()));
//
//            if (bindingResult.hasErrors() || !this.adminService.register(adminRegisterDTO)) {
//                redirectAttributes.addFlashAttribute("adminRegisterDTO", adminRegisterDTO);
//                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.adminRegisterDTO", bindingResult);
//                System.out.println("Errors");
//                return "redirect:/register";
//            }
//
//        } catch (Exception e) {
//            redirectAttributes.addAttribute("serverErrors", false);
//        }
//
//        return "redirect:/login";
//    }

    @ModelAttribute("adminRegisterDTO")
    public AdminRegisterDTO adminRegisterDTO(){
        return new AdminRegisterDTO();
    }

    @ModelAttribute
    public void addAttribute(Model model){
       model.addAttribute("serverErrors");
    }
}

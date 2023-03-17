package com.homecode.customer.web;

import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.EmailDTO;
import com.homecode.library.model.view.CustomerProfileModelsView;
import com.homecode.library.model.view.CustomerProfileView;
import com.homecode.library.service.CustomerUserService;
import com.homecode.library.service.impl.EmailServiceImpl;
import com.homecode.library.service.impl.ModelServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PageController {

    private final CustomerUserService customerUserService;
    private final ModelServiceImpl modelService;

    private final EmailServiceImpl emailService;

    public PageController(CustomerUserService customerUserService, ModelServiceImpl modelService, EmailServiceImpl emailService) {
        this.customerUserService = customerUserService;
        this.modelService = modelService;
        this.emailService = emailService;
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }


    @GetMapping("/user-acount")
    public String accountUser(Principal principal, Model model) {

        if (principal == null) {
            return "redirect:/login";
        }

        UserEntity user = this.customerUserService.findUserByUsername(principal.getName());
        CustomerProfileView profileView = new CustomerProfileView()
                .setEmail(user.getEmail())
                .setFistName(user.getFirstName())
                .setLastName(user.getLastName());
        model.addAttribute("userProfile", profileView);

        List<CustomerProfileModelsView> userModels = this.modelService.getUserModels(user.getUserUploadedModels());
        model.addAttribute("userModels", userModels);

        List<CustomerProfileModelsView> userDownloadedModels = this.modelService.getUserModels(user.getPurchasedModels());
        model.addAttribute("userDownloadedModels", userDownloadedModels);

        List<CustomerProfileModelsView> userLikedModels = this.modelService.getUserModels(user.getLikedModels());
        model.addAttribute("userLikedModels", userLikedModels);
        //TODO refactoring get all from userService
        return "/user-acount";
    }

    @PostMapping("/subscribe")
    public String subscribe(EmailDTO emailDTO) {
        this.emailService.saveEmail(emailDTO);
        return "redirect:/";
    }

    @ModelAttribute("emailDTO")
    public EmailDTO emailDTO() {
        return new EmailDTO();
    }


}

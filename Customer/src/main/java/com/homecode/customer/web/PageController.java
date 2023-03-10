package com.homecode.customer.web;

import com.homecode.library.model.UserEntity;
import com.homecode.library.model.view.CustomerProfileModelsView;
import com.homecode.library.model.view.CustomerProfileView;
import com.homecode.library.service.CustomerUserService;
import com.homecode.library.service.impl.ModelServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PageController {

    private final CustomerUserService customerUserService;
    private final ModelServiceImpl modelService;

    public PageController(CustomerUserService customerUserService, ModelServiceImpl modelService) {
        this.customerUserService = customerUserService;
        this.modelService = modelService;
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }


    @GetMapping("/user-acount")
    public String accountUser(Principal principal, Model model) {
        UserEntity user = this.customerUserService.findUserByUsername(principal.getName());
        CustomerProfileView profileView = new CustomerProfileView()
                .setEmail(user.getEmail())
                .setFistName(user.getFirstName())
                .setLastName(user.getLastName());
        model.addAttribute("userProfile", profileView);

        List<CustomerProfileModelsView> userPurchasedModels = this.modelService.getUserModels(user.getPurchasedModels());
        model.addAttribute("userPurchasedModels", userPurchasedModels);

        List<CustomerProfileModelsView> userLikedModels = this.modelService.getUserModels(user.getLikedModels());
        model.addAttribute("userLikedModels", userLikedModels);

        return "/user-acount";
    }


}

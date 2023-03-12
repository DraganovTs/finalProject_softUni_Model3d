package com.homecode.admin.web;

import com.homecode.library.model.dto.IpAddressDTO;
import com.homecode.library.service.impl.BlackListServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import static com.homecode.admin.constants.Messages.*;

@Controller
public class AdminIpController {

    private final BlackListServiceImpl blackListService;

    public AdminIpController(BlackListServiceImpl blackListService) {
        this.blackListService = blackListService;
    }

    @GetMapping("/user-ban")
    public String banUsers(Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("addresses", this.blackListService.findAll());
        return "user-ban";
    }

    @PostMapping("/user-ban")
    public String AddUsersToBanList(IpAddressDTO ipAddressDTO,
                                    RedirectAttributes redirectAttributes){

        try {
            if (this.blackListService.isBlacklisted(ipAddressDTO.getIpAddress())) {
                redirectAttributes.addFlashAttribute("existIp", USER_ALREADY_BANNED);
                return "redirect:/user-ban";
            }
            this.blackListService.addIpAddress(ipAddressDTO.getIpAddress());
            redirectAttributes.addFlashAttribute("success", ADD_SUCCESSFULLY);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failed", SERVER_NOT_WORKING);
        }


        return "redirect:/user-ban";
    }

    @GetMapping("/delete-ip-from-list/{id}")
    public String deleteById(@PathVariable( value = "id") Long id){
        this.blackListService.removeIpAddress(id);
        return "redirect:/user-ban";
    }

    @ModelAttribute("ipAddressDTO")
    public IpAddressDTO ipAddressDTO(){
        return new IpAddressDTO();
    }


}

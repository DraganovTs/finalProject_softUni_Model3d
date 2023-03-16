package com.homecode.customer.web;

import com.homecode.library.model.dto.EmailDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ModeratorController {

    @GetMapping("/moderator")
    public String accountUser(){
        return "/index-moderator";
    }


    @ModelAttribute("emailDTO")
    public EmailDTO emailDTO() {
        return new EmailDTO();
    }
}

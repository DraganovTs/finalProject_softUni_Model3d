package com.homecode.customer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModeratorController {

    @GetMapping("/moderator")
    public String accountUser(){
        return "/index-moderator";
    }
}

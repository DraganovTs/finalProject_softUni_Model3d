package com.homecode.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    @GetMapping("/moderator-add")
    public String addModerator(){
        return "moderator-add";
    }

    @GetMapping("/ban-users")
    public String banUsers(){
        return "users-blacklist";
    }
}

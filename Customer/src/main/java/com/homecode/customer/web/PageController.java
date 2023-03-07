package com.homecode.customer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/about-us")
    public String aboutUs(){
        return "about-us";
    }
}

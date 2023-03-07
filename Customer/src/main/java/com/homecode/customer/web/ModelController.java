package com.homecode.customer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModelController {

    @GetMapping("/add-model")
    public String addModel(){
        return "model-add";
    }


    @GetMapping("/models-all")
    public String allModels(){
        return "model-all";
    }
}

package com.homecode.admin.web;

import com.homecode.library.service.impl.CategoryModelServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    private final CategoryModelServiceImpl categoryService;

    public CategoryController(CategoryModelServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String categories(Model model){
        model.addAttribute("categories",this.categoryService.findAll());
        return "categories";
    }
}

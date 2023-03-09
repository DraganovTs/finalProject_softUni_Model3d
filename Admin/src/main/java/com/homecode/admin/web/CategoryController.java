package com.homecode.admin.web;

import com.homecode.library.model.CategoryModelEntity;
import com.homecode.library.service.impl.CategoryModelServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @PostMapping("/add-category")
    public String add(CategoryModelEntity category,
                      RedirectAttributes redirectAttributes){
        try {
            this.categoryService.save(category);
            redirectAttributes.addFlashAttribute("success","Added successfully");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("failed","Failed");
        }
        return "redirect:/categories";
    }

    @ModelAttribute("categoryNew")
    public CategoryModelEntity categoryNew(){
        return new CategoryModelEntity();
    }
}

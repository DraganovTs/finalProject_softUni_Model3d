package com.homecode.admin.web;


import com.homecode.library.model.dto.CategoryDTO;
import com.homecode.library.service.impl.CategoryModelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class CategoryController {

    private final CategoryModelServiceImpl categoryService;

    public CategoryController(CategoryModelServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String categories(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("categories", this.categoryService.findAll());
        return "categories";
    }


    @PostMapping("/add-category")
    public String add(@Valid CategoryDTO categoryDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("categoryDTO", categoryDTO);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.categoryDTO", bindingResult);
                return "redirect:/categories";
            }
            if (!categoryService.findCategoryByName(categoryDTO)) {
                redirectAttributes.addFlashAttribute("existCategory", "Category already exist");
                return "redirect:/categories";
            }
            this.categoryService.save(categoryDTO);
            redirectAttributes.addFlashAttribute("success", "Added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failed", "Server is down!");
        }
        return "redirect:/categories";
    }


    @GetMapping("/delete-category/{id}")
    public String deleteById(@PathVariable( value = "id") Long id){
        this.categoryService.deleteById(id);
        return "redirect:/categories";
    }
    @GetMapping("/enable-category/{id}")
    public String enableById(@PathVariable( value = "id") Long id){
        this.categoryService.enableById(id);
        return "redirect:/categories";
    }

    @ModelAttribute("categoryDTO")
    public CategoryDTO categoryDTO() {
        return new CategoryDTO();
    }
}

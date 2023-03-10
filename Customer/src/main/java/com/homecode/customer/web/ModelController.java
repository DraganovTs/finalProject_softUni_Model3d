package com.homecode.customer.web;

import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.service.impl.CategoryModelServiceImpl;
import com.homecode.library.service.impl.ModelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class ModelController {

    private final ModelServiceImpl modelService;
    private final CategoryModelServiceImpl categoryModelService;

    public ModelController(ModelServiceImpl modelService, CategoryModelServiceImpl categoryModelService) {
        this.modelService = modelService;
        this.categoryModelService = categoryModelService;
    }

    @GetMapping("/add-model")
    public String addModel(Model model) {
        model.addAttribute("categories", this.categoryModelService.findAll());
        return "model-add";
    }

    @PostMapping("/add-model")
    public String uploadModel(@Valid ModelUploadDTO modelUploadDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal,
                              @RequestParam("image") MultipartFile imageModel) {

        try {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("modelUploadDTO", modelUploadDTO);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelUploadDTO", bindingResult);
                System.out.println("Errors");
                return "redirect:/add-model";
            }
            if (!this.modelService.isExistInDB(modelUploadDTO)) {
                redirectAttributes.addFlashAttribute("alreadyInDb", "This model is already uploaded!");
                return "redirect:/add-model";
            }

            this.modelService.uploadModel(imageModel, modelUploadDTO, principal.getName());


        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failed", "Server is down!");
            return "redirect:/add-model";
        }

        redirectAttributes.addFlashAttribute("success", "Your model is uploaded and waiting to be approved");


        return "redirect:/add-model";
    }


    @GetMapping("/models-all")
    public String allModels() {
        return "model-all";
    }

    @ModelAttribute("modelUploadDTO")
    public ModelUploadDTO modelUploadDTO() {
        return new ModelUploadDTO();
    }
}

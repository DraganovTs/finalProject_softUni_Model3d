package com.homecode.customer.web;

import com.homecode.library.model.dto.EmailDTO;
import com.homecode.library.model.view.ModelsShowAllView;
import com.homecode.library.service.impl.ModelServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ModeratorController {

    private final ModelServiceImpl modelService;

    public ModeratorController(ModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/moderator")
    public String accountUser(Model model){
        List<ModelsShowAllView> allModelsView = this.modelService.getAllModelsForModerator();
        model.addAttribute("modelsNumber", allModelsView.size());
        model.addAttribute("allModels", allModelsView);
        return "all-models-moderator";
    }

    @GetMapping("/model-approve/{id}")
    public String approveModel(@PathVariable(value = "id") Long id) {
        this.modelService.approveModelById(id);
        return "redirect:/all-models-moderator";
    }

    @ModelAttribute("emailDTO")
    public EmailDTO emailDTO() {
        return new EmailDTO();
    }
}

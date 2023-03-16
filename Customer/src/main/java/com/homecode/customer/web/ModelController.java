package com.homecode.customer.web;

import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.dto.EmailDTO;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.service.FileService;
import com.homecode.library.service.impl.CategoryModelServiceImpl;
import com.homecode.library.service.impl.CustomerUserServiceImpl;
import com.homecode.library.service.impl.ModelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ModelController {

    private final ModelServiceImpl modelService;
    private final CategoryModelServiceImpl categoryModelService;
    private final FileService fileService;

    private final CustomerUserServiceImpl customerUserService;

    public ModelController(ModelServiceImpl modelService, CategoryModelServiceImpl categoryModelService, FileService fileService, CustomerUserServiceImpl customerUserService) {
        this.modelService = modelService;
        this.categoryModelService = categoryModelService;
        this.fileService = fileService;
        this.customerUserService = customerUserService;
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
                              @RequestParam("imageModel") MultipartFile imageModel,
                              @RequestParam("zipModel") MultipartFile zipModel) {

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

            this.modelService.uploadModel(imageModel, zipModel, modelUploadDTO, principal.getName());


        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failed", "Server is down!");
            return "redirect:/add-model";
        }

        //TODO get send user to modelservice and send user to userService
        redirectAttributes.addFlashAttribute("success", "Your model is uploaded and waiting to be approved");


        return "redirect:/add-model";
    }


    @GetMapping("/models-all")
    public String allModels(Model model) {
        List<ModelEntity> allModelsView = this.modelService.getAllModels();
        model.addAttribute("modelsNumber", allModelsView.size());
        model.addAttribute("allModels", allModelsView);
        return "model-all";
    }

    @GetMapping("/download-model/{fileId}")
    public HttpEntity<byte[]> downloadZipModel(@PathVariable(value = "fileId") Long fileId) {

        var fileDownloadModel = this.fileService.getFileById(fileId).orElseThrow();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MimeTypeUtils.parseMimeType(fileDownloadModel.getContentType())));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileDownloadModel.getFileName());
        headers.setContentLength(fileDownloadModel.getFileData().length);


        return new HttpEntity<>(fileDownloadModel.getFileData(), headers);
    }


    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", this.modelService.findById(id));
        return "model-detail";
    }

    @GetMapping("/model-like/{modelId}")
    public String likeModel(Principal principal, @PathVariable(value = "modelId") Long modelId) {

        if (principal == null) {
            return "redirect:/login";
        }

        ModelEntity model = this.modelService.findById(modelId);
        this.customerUserService.likeModel(principal.getName(), model);
        this.modelService.likeModel(model);
        return "redirect:/models-all";
    }

    @ModelAttribute("modelUploadDTO")
    public ModelUploadDTO modelUploadDTO() {
        return new ModelUploadDTO();
    }

    @ModelAttribute("emailDTO")
    public EmailDTO emailDTO() {
        return new EmailDTO();
    }


}

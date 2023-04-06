package com.homecode.customer.web;

import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.EmailDTO;
import com.homecode.library.model.dto.ModelUploadDTO;
import com.homecode.library.model.view.ModelsShowAllView;
import com.homecode.library.service.FileService;
import com.homecode.library.service.impl.CategoryModelServiceImpl;
import com.homecode.library.service.impl.CustomerUserServiceImpl;
import com.homecode.library.service.impl.ModelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static com.homecode.customer.constants.Messages.*;


@Controller
public class ModelController {

    private final ModelServiceImpl modelService;
    private final CategoryModelServiceImpl categoryModelService;
    private final FileService fileService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final CustomerUserServiceImpl customerUserService;

    public ModelController(ModelServiceImpl modelService, CategoryModelServiceImpl categoryModelService, FileService fileService, KafkaTemplate<String, String> kafkaTemplate, CustomerUserServiceImpl customerUserService) {
        this.modelService = modelService;
        this.categoryModelService = categoryModelService;
        this.fileService = fileService;
        this.kafkaTemplate = kafkaTemplate;
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

        if (principal == null) {
            return "redirect:/login";
        }

        try {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("modelUploadDTO", modelUploadDTO);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelUploadDTO", bindingResult);
                System.out.println("Errors");
                return "redirect:/add-model";
            }
            if (!this.modelService.isExistInDB(modelUploadDTO)) {
                redirectAttributes.addFlashAttribute("alreadyInDb", MODEL_ALREADY_IN_DB);
                return "redirect:/add-model";
            }

            UserEntity user = this.customerUserService.findUserByUsername(principal.getName());
            this.modelService.uploadModel(imageModel, zipModel, modelUploadDTO, user);


        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failed", SERVER_IS_DOWN);
            return "redirect:/add-model";
        }

        this.customerUserService.userAddModel(principal.getName(), modelUploadDTO);
        redirectAttributes.addFlashAttribute("success", MODEL_UPLOADED_SUCCESSFULLY);

        envMessage("Model added successfully" + modelUploadDTO.getName());
        return "redirect:/add-model";
    }


    @GetMapping("/models-all")
    public String allModels(Model model, @RequestParam Optional<String> keyword) {

        List<ModelsShowAllView> allModelsView = this.modelService.getAllModelsByKeyword(keyword.orElse("_"));

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

        envMessage("Model downloaded successfully" + fileDownloadModel.getFileName());
        return new HttpEntity<>(fileDownloadModel.getFileData(), headers);
    }


    @GetMapping("/model-detail/{id}")
    public String productDetail(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("userCredits", this.customerUserService.getUserCredits(principal.getName()));
        }
        model.addAttribute("model", this.modelService.showModelDetailById(id));
        return "model-detail";
    }

    @GetMapping("/model-like/{modelId}")
    public String likeModel(Principal principal, @PathVariable(value = "modelId") Long modelId) {


        ModelEntity model = this.modelService.findById(modelId);
        if (this.customerUserService.likeModel(principal.getName(), model)) {
            this.modelService.likeModel(model);
        }
        return "redirect:/models-all";
    }


    @GetMapping("/download-model-user/{id}")
    public String UserDownloadModel(@PathVariable(value = "id") Long id, Principal principal) {


        if (!this.customerUserService.userDownloadModel(principal.getName(), id)) {
            return "redirect:/model-detail/{id}";
        }

        this.modelService.modelDownloaded(id);

        return "redirect:/download-model/{id}";
    }

    @ModelAttribute("modelUploadDTO")
    public ModelUploadDTO modelUploadDTO() {
        return new ModelUploadDTO();
    }

    @ModelAttribute("emailDTO")
    public EmailDTO emailDTO() {
        return new EmailDTO();
    }

    private void envMessage(String massage){
        kafkaTemplate.send("my-topic",massage);
    }

}

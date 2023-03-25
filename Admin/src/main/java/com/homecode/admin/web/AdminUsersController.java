package com.homecode.admin.web;

import com.homecode.library.model.dto.UserAddRolesDto;
import com.homecode.library.service.impl.AdminServiceImpl;
import com.homecode.library.service.impl.CustomerUserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import static com.homecode.admin.constants.Messages.*;

@Controller
public class AdminUsersController {
    private final AdminServiceImpl adminService;
    private final CustomerUserServiceImpl customerUserService;

    public AdminUsersController(AdminServiceImpl adminService, CustomerUserServiceImpl customerUserService) {
        this.adminService = adminService;
        this.customerUserService = customerUserService;
    }

    @GetMapping("/user-roles-admin")
    public String userRolesAdmin(Model model, Principal principal) {
        model.addAttribute("userAdmins", this.adminService.findAllAdmins());

        return "user-roles-admin";
    }

    @PostMapping("/user-roles-admin")
    public String editAdminRoles(UserAddRolesDto adminAddRolesDto,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (this.adminService.findAdminEntityByEmail(adminAddRolesDto)) {
                redirectAttributes.addFlashAttribute("existAdmin", USER_ALREADY_ADMIN);
                return "redirect:/user-roles-admin";
            }
            this.adminService.addAdminRole(adminAddRolesDto);
            redirectAttributes.addFlashAttribute("successAdmin", ADD_SUCCESSFULLY);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failedAdmin", SERVER_NOT_WORKING);
        }

        return "redirect:/user-roles-admin";
    }
    @GetMapping("/delete-admin-from-list/{id}")
    public String deleteAdmin(@PathVariable(value = "id") Long id){
        this.adminService.deleteAdminRole(id);
        return "redirect:/user-roles-admin";
    }

    @GetMapping("/user-roles-moderator")
    public String userRolesModerator(Model model, Principal principal) {
        model.addAttribute("usersModerators",this.customerUserService.findAllModerators());
        return "user-roles-moderator";
    }
    @PostMapping("/user-roles-moderator")
    public String editModeratorRoles(UserAddRolesDto userAddRolesDto,
                                     RedirectAttributes redirectAttributes) {
        try {
            if (this.customerUserService.findUserByUsername(userAddRolesDto)) {
                redirectAttributes.addFlashAttribute("existModerator", USER_ALREADY_MODERATOR);
                return "redirect:/user-roles-moderator";
            }
            this.customerUserService.createModerator(userAddRolesDto);
            redirectAttributes.addFlashAttribute("successModerator", ADD_SUCCESSFULLY);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("failedModerator", SERVER_NOT_WORKING);
        }

        return "redirect:/user-roles-moderator";
    }

    @GetMapping("/delete-moderator-from-list/{id}")
    public String deleteModerator(@PathVariable(value = "id") Long id){
        this.customerUserService.deleteModeratorRoleById(id);
        return "redirect:/user-roles-moderator";
    }


    @ModelAttribute("adminAddRolesDto")
    public UserAddRolesDto adminAddRolesDto(){
        return new UserAddRolesDto();
    }
    @ModelAttribute("userAddRolesDto")
    public UserAddRolesDto userAddRolesDto(){
        return new UserAddRolesDto();
    }


}

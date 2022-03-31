package com.qsa.quitSmokingApp.controller;

import com.qsa.quitSmokingApp.logic.AppUserService;
import com.qsa.quitSmokingApp.model.projection.NewUserWriteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/newUser")
public class UserViewController {

    private AppUserService service;


    UserViewController(AppUserService service){
        this.service = service;
    }

    @GetMapping
    String showNewUserRegistration(Model model){
        model.addAttribute("newUser", new NewUserWriteModel());
        return "newUser";
    }

    @PostMapping
    String registerNewUser(@ModelAttribute("newUser")  @Valid NewUserWriteModel newUserWriteModel, BindingResult bindingResult, Model model ){
        if(bindingResult.hasErrors()){
            return "newUser";
        }
        var result = service.createNewUser(newUserWriteModel);
        model.addAttribute("newUser", new NewUserWriteModel());
        model.addAttribute("message", "You have been registered");
        return "newUser";
    }

}

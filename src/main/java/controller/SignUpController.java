package controller;

import entity.RegistrationForm;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/signUp")
@SessionAttributes("registrationForm")
public class SignUpController {

    @GetMapping
    public String setupForm(Model model){
        model.addAttribute("registrationForm", new RegistrationForm());
        return "signUp";
    }

    @PostMapping
    public String submitForm(
//            @Valid @SessionAttribute("user") User user,
//            BindingResult bindingResult, SessionStatus sessionStatus
    ){
//        if(bindingResult.hasErrors()){
//            return "/signUp";
//        }
//        sessionStatus.setComplete();
        return "main";
    }

}

package controller;

import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@SessionAttributes("user")
@RequestMapping("/signIn")
public class SignInController {

    @GetMapping
    public String setupForm(Model model) {
        model.addAttribute("user", new User());
        return "signIn";
    }

    @PostMapping
    public String submitForm(
//            @Valid @SessionAttribute("user") User user,
//            BindingResult bindingResult,
//            SessionStatus sessionStatus
    ){
//        if (bindingResult.hasErrors()) {
//            return "/signIn";
//        }
//        sessionStatus.setComplete();
        return "redirect:/main";
    }


}

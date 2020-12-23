package controller;

import dao.UserDAO;
import dto.RegistrationForm;
import entity.User;
import exception.FailedRegistrationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/signUp")
@SessionAttributes("registrationForm")
public final class SignUpController {

    private final UserDAO userDAO;

    public SignUpController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String setupForm(Model model){
        model.addAttribute("registrationForm", new RegistrationForm());
        return "signUp";
    }

    @PostMapping
    public String submitForm(
            @Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            HttpServletRequest httpServletRequest){
        if(bindingResult.hasErrors()){
            return "/signUp";
        }
        User user = new User();
        user.setLogin(registrationForm.getLogin());
        user.setPassword(registrationForm.getPassword());
        user.setEmail(registrationForm.getEmail());
        try {
            userDAO.register(user);
            httpServletRequest.getSession().setAttribute("user", user);
        }catch (FailedRegistrationException e){
            bindingResult.rejectValue("name", "formError.loginIsTaken", "Login is taken");
            return "/signUp";
        }
        sessionStatus.setComplete();
        return "redirect:/main";
    }

}

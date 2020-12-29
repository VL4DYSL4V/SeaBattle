package controller;

import dao.UserDAO;
import dto.RegistrationForm;
import entity.User;
import exception.EmailIsTakenException;
import exception.FailedRegistrationException;
import exception.LoginIsTakenException;
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
    public String setupForm(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("registrationForm", new RegistrationForm());
        if(httpServletRequest.getSession().getAttribute("user") != null){
            httpServletRequest.getSession().removeAttribute("user");
        }
        return "signUp";
    }

    @PostMapping
    public String submitForm(
            @Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return "/signUp";
        }
        User user = User.createSeamanApprentice(registrationForm.getLogin(),
                registrationForm.getPassword(),
                registrationForm.getEmail());
        try {
            userDAO.register(user);
            httpServletRequest.getSession().setAttribute("user", user);
        } catch (FailedRegistrationException e) {
            Class<?> causeClass = e.getCause().getClass();
            if (causeClass == LoginIsTakenException.class) {
                bindingResult.rejectValue("login", "formError.loginIsTaken",
                        "Login is taken");
            }else if(causeClass == EmailIsTakenException.class){
                bindingResult.rejectValue("email", "formError.emailIsTaken",
                        "Email is taken");
            } else {
                e.printStackTrace();
            }
            return "/signUp";
        }
        sessionStatus.setComplete();
        return "redirect:/main";
    }

}

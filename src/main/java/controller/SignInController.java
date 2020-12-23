package controller;

import dao.UserDAO;
import dto.SignInForm;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/signIn")
@SessionAttributes("signInForm")
public final class SignInController {

    private final UserDAO userDAO;

    public SignInController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String setupForm(Model model) {
        model.addAttribute("signInForm", new SignInForm());
        return "signIn";
    }

    @PostMapping
    public String submitForm(@Valid @ModelAttribute("signInForm") SignInForm signInForm,
                             BindingResult bindingResult,
                             SessionStatus sessionStatus,
                             HttpServletRequest httpServletRequest){
        if (bindingResult.hasErrors()) {
            return "/signIn";
        }
        User user = userDAO.getByLogin(signInForm.getLogin());
        if(user == null){
            bindingResult.rejectValue("login", "formError.noSuchUser",
                    "No such user");
            return "/signIn";
        }else if(!Objects.equals(user.getPassword(), signInForm.getPassword())){
            bindingResult.rejectValue("password", "formError.wrongPassword",
                    "Wrong password");
            return "/signIn";
        }
        httpServletRequest.getSession().setAttribute("user", user);
        sessionStatus.setComplete();
        return "redirect:/main";
    }


}

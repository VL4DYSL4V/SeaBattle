package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/main")
public final class MainController {

    @GetMapping
    public String mainPage(HttpServletRequest request){
        if(request.getSession().getAttribute("user") == null){
            return "welcome";
        }
        return "main";
    }

    @GetMapping("/battle")
    public String findOpponent(){
        return "";
    }

}

package org.example.labgticsz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping(value = {"/login"," ","/"})
    public String loginForm(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("usuario") == null){
            return "loginFrm";
        }else{
            return "redirect:/users/list";
        }
    }
}

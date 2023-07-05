package com.example.backendsvc.view;

import com.example.backendsvc.domain.User;
import com.example.backendsvc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserViewController {
    private UserService userService;
    public UserViewController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/users")
    public String getAllUsers(ModelAndView modelAndView){
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        return "viewusersthymeleaf.html";
    }
}

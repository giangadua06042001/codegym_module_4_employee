package com.example.employee.controller;

import com.example.employee.model.User;
import com.example.employee.service.IEmployeeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;


    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @RequestMapping("/login")
    public String Index(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        return "employee/login";
    }

    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("user") User user, Model model, @CookieValue(value = "setUser", defaultValue = "") String setUser,
                          HttpServletResponse response, HttpServletRequest request) {
        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("12345")) {
            if (user.getEmail() != null)
                setUser = user.getEmail();
            Cookie cookie = new Cookie("setUser", setUser);
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            Cookie[] cookies = request.getCookies();

            model.addAttribute("cookieValue", cookie.getValue());
//            for (Cookie ck : cookies) {
//                //display only the cookie with the name 'setUser'
//                if (ck.getName().equals("setUser")) {
//                    String cookieValue = ck.getValue();
//                    model.addAttribute("cookieValue", cookieValue);
//                    break;
//                } else {
//                    ck.setValue("");
//                    String cookieValue = ck.getValue();
//                    model.addAttribute("cookieValue", cookieValue);
//                    break;
//                }
//            }
            model.addAttribute("message", "Login success. Welcome ");
        } else {
            user.setEmail("");
            Cookie cookie = new Cookie("setUser", setUser);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("employee",employeeService.findAll());
            model.addAttribute("message", "Login failed. Try again.");
        }
        return "employee/list";
    }
}
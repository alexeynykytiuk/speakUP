package com.example.speakup.controller.loginController;

import com.example.speakup.auth.AuthService;
import com.example.speakup.entity.teacher.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/")
@RequiredArgsConstructor
@Controller
public class LoginController {
    private final AuthService authService;

    @GetMapping
    public void get(HttpServletResponse resp){

        if (authService.hasAuthority(Role.TEACHER.toString())){
            try {
                resp.sendRedirect("/report");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (authService.hasAuthority(Role.HEAD_OF_STUDIES.toString())){
            try {
                resp.sendRedirect("/teacher");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

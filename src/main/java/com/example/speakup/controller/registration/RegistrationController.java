package com.example.speakup.controller.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/createUser")
@Controller
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping
    public ModelAndView get(){
        ModelAndView modelAndView = new ModelAndView("createUser");
        return modelAndView;
    }

    @PostMapping
    public void creteUser(@RequestParam Map<String,String> map, HttpServletResponse resp){
        registrationService.createUser(map,resp);
    }
}

package com.example.speakup.controller.errorController;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/error")
@Controller
public class ErrorController {

    @GetMapping
    public ModelAndView get(){
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }

    @GetMapping("/createUser")
    public ModelAndView getErrorCreateUser(){
        ModelAndView modelAndView = new ModelAndView("errorCreateUser");
        return modelAndView;
    }

    @GetMapping("/emailExists")
    public ModelAndView getErrorEmailExists(){
        ModelAndView modelAndView = new ModelAndView("errorEmailExists");
        return modelAndView;
    }

}

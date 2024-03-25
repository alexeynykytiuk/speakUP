package com.example.speakup.controller.calculateController;

import com.example.speakup.controller.teacherController.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/calculate")
@Controller
public class CalculateController {
    private final CalculateService calculateService;
    private final TeacherService teacherService;

    @GetMapping
    public ModelAndView get(@RequestParam Map<String,String> map, Authentication authentication){
        ModelAndView modelAndView = new ModelAndView("calculate");
        modelAndView.addObject("years", calculateService.getYears());
        modelAndView.addObject("months", calculateService.getMonth());
        modelAndView.addObject("days", calculateService.getDay());
        if (!map.isEmpty()){
            modelAndView.addObject("dates",calculateService.date(map));
            modelAndView.addObject("hours",calculateService.sum(map,authentication));
            modelAndView.addObject("sumPrice",calculateService.sumPrice(map,authentication));
            modelAndView.addObject("exist", "true");
        } else {
            modelAndView.addObject("exist", "false");
        }
        return modelAndView;
    }

    @GetMapping("/teacher")
    public ModelAndView getCalculateTeacher(@RequestParam Map<String,String> map){
        ModelAndView modelAndView = new ModelAndView("headCalculate");
        modelAndView.addObject("teachers",teacherService.getAllTeacher());
        modelAndView.addObject("years",calculateService.getYears());
        modelAndView.addObject("months",calculateService.getMonth());
        modelAndView.addObject("days",calculateService.getDay());
        if (!map.isEmpty()){
            modelAndView.addObject("dates",calculateService.date(map));
            modelAndView.addObject("hours",calculateService.sumHead(map));
            modelAndView.addObject("teacher",teacherService.getTeacherById(Integer.valueOf(map.get("teacherId"))));
            modelAndView.addObject("counts", calculateService.mapGroupCount(map));
            modelAndView.addObject("sumPrice",calculateService.sumPriceForHeadOfStudies(map));
            modelAndView.addObject("exist", "true");
        } else {
            modelAndView.addObject("exist", "false");
        }
        return modelAndView;
    }
}

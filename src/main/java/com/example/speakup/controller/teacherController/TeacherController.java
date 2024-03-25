package com.example.speakup.controller.teacherController;

import com.example.speakup.entity.teacher.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@RequestMapping("/teacher")
@RequiredArgsConstructor
@Controller
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ModelAndView get(){
        ModelAndView modelAndView = new ModelAndView("teacher");
        modelAndView.addObject("teachers",teacherService.getAllTeacher());
        modelAndView.addObject("roles", new ArrayList<Role>(Arrays.asList(Role.TEACHER,Role.HEAD_OF_STUDIES)));
        return modelAndView;
    }

    @GetMapping("/date/*")
    public ModelAndView getTeacherReports(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView("teacherReportById");
        String[] split = req.getRequestURI().split("/");
        Integer id = Integer.valueOf(split[3]);
        modelAndView.addObject("reports",teacherService.getReportsByTeacher(id));
        modelAndView.addObject("teacher",teacherService.getTeacherById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public void deleteTeacher(@RequestParam Map<String,String> map, HttpServletResponse resp){
        teacherService.deleteTeacher(map);

        try {
            resp.sendRedirect("/teacher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/update")
    public void updateTeacher(@RequestParam Map<String,String> map,HttpServletResponse resp,HttpServletRequest req){
        teacherService.updateTeacher(map);
        try {
            resp.sendRedirect("/teacher");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

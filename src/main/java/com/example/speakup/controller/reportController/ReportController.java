package com.example.speakup.controller.reportController;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/report")
@Controller
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    public ModelAndView getReportPage(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView("reportList");
        modelAndView.addObject("reports",reportService.getReportByTeacher(authentication));
        modelAndView.addObject("teacher",1);
        modelAndView.addObject("sumForMonth",reportService.getSumForMont(authentication));
        return modelAndView;
    }

    @GetMapping("/date/*")
    public ModelAndView getReportByIdPage(HttpServletRequest req,Authentication authentication){
        String[] split = req.getRequestURI().split("/");
        Integer id = Integer.parseInt(split[3]);
        ModelAndView modelAndView = new ModelAndView("report");
        modelAndView.addObject("report",reportService.getById(id));
        modelAndView.addObject("groups",reportService.getAllGroupByReport(id));
        modelAndView.addObject("groupList",reportService.getAllGroupByTeacher(authentication));
        return modelAndView;
    }

    @PostMapping("/create")
    public void createReport(HttpServletResponse resp, Authentication authentication){
        reportService.createReport(resp,authentication);

    }

    @PostMapping("/delete")
    public void deleteReport(@RequestParam Map<String,String> map,HttpServletResponse resp){
        reportService.deleteReport(map,resp);
    }

    @PostMapping("/create/group")
    public void createGroupForReport(@RequestParam Map<String,String> map,HttpServletResponse resp){
        reportService.createGroupReport(map);

        try {
            resp.sendRedirect("/report/date/" + map.get("reportId"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/delete/group")
    public void deleteGroupByReport(@RequestParam Map<String,String> map, HttpServletResponse resp){
        reportService.deleteGroupByReport(map);

        try {
            resp.sendRedirect("/report/date/" + map.get("reportId"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

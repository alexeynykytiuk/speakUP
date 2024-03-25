package com.example.speakup.controller.groupController;

import com.example.speakup.entity.groups.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RequestMapping("/group")
@RequiredArgsConstructor
@Controller
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public ModelAndView getGroupPage(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView("groupList");
        List<Group> groupByTeacher = groupService.getGroupByTeacher(authentication);
        modelAndView.addObject("groups",groupByTeacher);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getGroupCreatePage(){
        ModelAndView modelAndView = new ModelAndView("groupCreate");
        return modelAndView;
    }

    @PostMapping("/create")
    public void postCreateGroup(@RequestParam Map<String,String> map, HttpServletResponse resp, Authentication authentication){
        groupService.createGroup(map,resp,authentication);
    }

    @PostMapping("/delete")
    public void postDeleteGroup(@RequestParam Map<String,String> map, HttpServletResponse resp){
        groupService.deleteGroup(map,resp);
    }

    @PostMapping("/update")
    public void updateGroup(@RequestParam Map<String,String> map, HttpServletResponse resp){
        groupService.updateGroup(map,resp);
    }
}

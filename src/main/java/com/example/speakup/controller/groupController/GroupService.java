package com.example.speakup.controller.groupController;

import com.example.speakup.auth.CustomUserDetails;
import com.example.speakup.entity.groupReport.GroupReportRepository;
import com.example.speakup.entity.groups.Group;
import com.example.speakup.entity.groups.GroupRepository;
import com.example.speakup.entity.report.ReportRepository;
import com.example.speakup.entity.teacher.Teacher;
import com.example.speakup.entity.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final GroupReportRepository groupReportRepository;
    private final ReportRepository reportRepository;

    public List<Group> getGroupByTeacher(Authentication authentication){
        Integer userId = ((CustomUserDetails) authentication.getPrincipal()).getUserId();
        Teacher teacher = teacherRepository.findById(userId).get();
        List<Group> groups = groupRepository.findAllByTeacher(teacher).get();
        return groups;
    }

    public void createGroup(Map<String,String> map, HttpServletResponse resp, Authentication authentication){
        String name = map.get("name");
        Integer price = Integer.parseInt(map.get("price"));
        Integer userId = ((CustomUserDetails) authentication.getPrincipal()).getUserId();
        Teacher byId = teacherRepository.findById(userId).get();

        Group group = new Group();
        group.setName(name);
        group.setPrice(price);
        group.setTeacher(byId);

        groupRepository.save(group);

        try {
            resp.sendRedirect("/group");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(Map<String,String> map, HttpServletResponse resp){
        int id = Integer.parseInt(map.get("id"));
        Group group = groupRepository.findById(id).get();
        groupRepository.delete(group);

        try {
            resp.sendRedirect("/group");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateGroup(Map<String,String> map, HttpServletResponse resp){
        String name = map.get("name");
        String price = map.get("price");
        int id = Integer.parseInt(map.get("id"));

        Group group = groupRepository.findById(id).get();

        if (!name.equals("")){
            group.setName(name);
        }

        if (!price.equals("")){
            group.setPrice(Integer.valueOf(price));
        }

        groupRepository.save(group);

        try {
            resp.sendRedirect("/group");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

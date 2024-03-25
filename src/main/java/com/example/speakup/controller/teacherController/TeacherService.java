package com.example.speakup.controller.teacherController;

import com.example.speakup.entity.report.Report;
import com.example.speakup.entity.report.ReportRepository;
import com.example.speakup.entity.teacher.Role;
import com.example.speakup.entity.teacher.Teacher;
import com.example.speakup.entity.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ReportRepository reportRepository;

    public Teacher getTeacherById(Integer id){
        return teacherRepository.findById(id).get();
    }

    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    public List<Report> getReportsByTeacher(Integer id){
        Teacher teacher = teacherRepository.findById(id).get();
        return reportRepository.findAllReportByTeacher(teacher).get();
    }

    public void deleteTeacher(Map<String,String> map){
        int id = Integer.parseInt(map.get("id"));
        Teacher teacher = teacherRepository.findById(id).get();
        teacherRepository.delete(teacher);
    }

    public void updateTeacher(Map<String,String> map){
        int id = Integer.parseInt(map.get("id"));
        String userName = map.get("userName");
        String password = map.get("password");
        String firstName = map.get("firstName");
        String lastName = map.get("lastName");
        String role = map.get("role");

        Teacher teacher = teacherRepository.findById(id).get();


        if (!userName.equals("")){
            teacher.setUserName(userName);
        }

        if (!password.equals("")){
            teacher.setPassword(password);
        }

        if (!firstName.equals("")){
            teacher.setFirstName(firstName);
        }

        if (!lastName.equals("")){
            teacher.setLastName(lastName);
        }

        if (!role.equals("Выбрать") && !role.equals("")){
            teacher.setRole(Role.valueOf(role));
        }

        teacherRepository.save(teacher);
    }

}

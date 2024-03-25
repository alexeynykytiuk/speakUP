package com.example.speakup.controller.registration;

import com.example.speakup.entity.teacher.Role;
import com.example.speakup.entity.teacher.Teacher;
import com.example.speakup.entity.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RegistrationService {
    private final TeacherRepository teacherRepository;

    public void createUser(Map<String,String> req, HttpServletResponse resp){
        String userName = req.get("userName");
        String password = req.get("password");
        String firstName = req.get("firstName");
        String lastName = req.get("lastName");
        Optional<Teacher> byUserName = teacherRepository.findByUserName(userName);
        if (userName.equals("") || password.equals("") || firstName.equals("") || lastName.equals("")){
            try {
                resp.sendRedirect("/error/createUser");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!byUserName.isEmpty()){
            try {
                resp.sendRedirect("/error/emailExists");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {

            Teacher teacher = new Teacher();
            teacher.setUserName(userName);
            teacher.setPassword(password);
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            teacher.setRole(Role.TEACHER);

            teacherRepository.save(teacher);

            try {
                resp.sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

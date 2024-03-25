package com.example.speakup.auth;

import com.example.speakup.entity.teacher.Teacher;
import com.example.speakup.entity.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final TeacherRepository teacherRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Teacher> byUserName = teacherRepository.findByUserName(username);
        if (byUserName.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        Teacher teacher = byUserName.get();
        String role = teacher.getRole().toString();
        String password = teacher.getPassword();

        return new CustomUserDetails(teacher);
    }

}

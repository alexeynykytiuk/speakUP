package com.example.speakup.entity.groups;

import com.example.speakup.entity.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
        Optional<List<Group>> findAllByTeacher(Teacher teacher);
}

package com.example.speakup.entity.groups;

import com.example.speakup.entity.teacher.Teacher;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer price;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}

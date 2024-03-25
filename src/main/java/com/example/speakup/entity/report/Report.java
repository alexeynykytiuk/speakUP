package com.example.speakup.entity.report;


import com.example.speakup.entity.teacher.Teacher;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;



@Data
@Entity(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDate dates;

    @Column
    private Integer numberOfLessons;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}

package com.example.speakup.entity.report;

import com.example.speakup.entity.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Integer> {
        Optional<List<Report>> findAllReportByTeacher(Teacher teacher);

        @Query(value = "select sum(r.number_of_lessons)\n" +
                "from report r\n" +
                "where r.teacher_id = :id and r.dates between :startDate and :endDate",nativeQuery = true)
        Integer sum(@Param("id") Integer id,@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}

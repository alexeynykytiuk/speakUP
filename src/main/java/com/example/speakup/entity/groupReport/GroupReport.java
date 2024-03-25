package com.example.speakup.entity.groupReport;

import com.example.speakup.entity.groups.Group;
import com.example.speakup.entity.report.Report;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class GroupReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;
}

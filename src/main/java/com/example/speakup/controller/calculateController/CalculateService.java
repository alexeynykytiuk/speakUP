package com.example.speakup.controller.calculateController;

import com.example.speakup.auth.CustomUserDetails;
import com.example.speakup.entity.groupReport.GroupReportRepository;
import com.example.speakup.entity.report.ReportRepository;
import com.example.speakup.entity.teacher.Teacher;
import com.example.speakup.entity.teacher.TeacherRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@RequiredArgsConstructor
@Service
public class CalculateService {
    private ReportRepository reportRepository;
    private GroupReportRepository groupReportRepository;
    private TeacherRepository teacherRepository;
    private List<String> day = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
            "17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"));
    private Map<String,String> month = new HashMap<>();
    private List<String> years = new ArrayList<>(Arrays.asList("2022","2023","2024","2025","2026","2027","2028","2029","2030","2031",
            "2032","2033","2034","2035","2036","2037","2038","2039","2040"));

    @Autowired
    public CalculateService(
            ReportRepository reportRepository,
            GroupReportRepository groupReportRepository,
            TeacherRepository teacherRepository
    ){
        this.reportRepository = reportRepository;
        this.groupReportRepository=groupReportRepository;
        this.teacherRepository=teacherRepository;

        month.put("1","Январь");
        month.put("2","Февраль");
        month.put("3","Март");
        month.put("4","Апрель");
        month.put("5","Май");
        month.put("6","Июнь");
        month.put("7","Июль");
        month.put("8","Август");
        month.put("9","Сентябрь");
        month.put("10","Октябрь");
        month.put("11","Ноябрь");
        month.put("12","Декабрь");
    }

    public Integer sum(Map<String,String> map, Authentication authentication){

        Date from = genFromDate(map);
        Date on = genOnDate(map);

        Integer userId = ((CustomUserDetails) authentication.getPrincipal()).getUserId();
        Integer sum = reportRepository.sum(userId, from, on);

        if (sum == null){
            return 0;
        }else {
            return sum;
        }
    }

    public String date(Map<String,String> map){
        String dayFrom = map.get("dayFrom");
        String monthFrom = map.get("monthFrom");
        String yearFrom = map.get("yearFrom");
        String dayOn = map.get("dayOn");
        String monthOn = map.get("monthOn");
        String yearOn = map.get("yearOn");

        String result = yearFrom + "." + monthFrom + "." + dayFrom + " - " + yearOn + "." + monthOn + "." + dayOn;
        return result;
    }

    public Integer sumPrice(Map<String,String> map,Authentication authentication){
        Date from = genFromDate(map);
        Date on = genOnDate(map);

        Integer userId = ((CustomUserDetails) authentication.getPrincipal()).getUserId();
        Integer sum = groupReportRepository.sumBetweenDate(userId,from,on);

        if (sum == null){
            return 0;
        }
        return sum;
    }

    public Integer sumHead(Map<String,String> map){
        Date from = genFromDate(map);
        Date on = genOnDate(map);

        Integer userId = Integer.valueOf(map.get("teacherId"));
        Integer sum = reportRepository.sum(userId, from, on);

        if (sum == null){
            return 0;
        }else {
            return sum;
        }
    }

    public List<Map<String,Integer>> mapGroupCount(Map<String,String> map){
        Date from = genFromDate(map);
        Date on = genOnDate(map);
        int teacherId = Integer.parseInt(map.get("teacherId"));

        return groupReportRepository.mapGroupCount(teacherId,from,on);
    }

    private Date genFromDate(Map<String,String> map){
        String from = map.get("yearFrom") + "-" + map.get("monthFrom") + "-" + map.get("dayFrom");
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");

        try {
            Date fromDate = format.parse(from);
            return fromDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Date genOnDate(Map<String,String> map){
        String on = map.get("yearOn") + "-" + map.get("monthOn") + "-" + map.get("dayOn");
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");

        try {
            Date onDate = format.parse(on);
            return onDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer sumPriceForHeadOfStudies(Map<String,String> map){
        Date from = genFromDate(map);
        Date on = genOnDate(map);
        int teacherId = Integer.parseInt(map.get("teacherId"));

        return groupReportRepository.sumBetweenDate(teacherId,from,on);
    }
}

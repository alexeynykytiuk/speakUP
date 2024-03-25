package com.example.speakup.controller.exportController;

import com.example.speakup.entity.report.Report;
import com.example.speakup.entity.report.ReportRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class ExportService {

    private ReportRepository reportRepository;

    public List<Report> exportReportToExel(HttpServletResponse response) throws IOException {
        List<Report> listReport = reportRepository.findAll();
        ExelExportUtils exportUtils = new ExelExportUtils(listReport);
        exportUtils.exportDataToExel(response);
        return listReport;
    }
}

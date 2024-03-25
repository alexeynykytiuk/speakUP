package com.example.speakup.controller.exportController;
import com.example.speakup.entity.report.Report;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExelExportUtils {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Report> listReport;

    public ExelExportUtils(List<Report> listReport) {
        this.listReport = listReport;
        workbook = new XSSFWorkbook();
    }

    private void createCells(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if( value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if( value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if( value instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            cell.setCellValue(((LocalDate) value).format(formatter));
        } else if( value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void createHeaderRow() {
        sheet = workbook.createSheet("Report");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        createCells(row, 0, "Report", style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        row = sheet.createRow(1);
        createCells(row, 0, "id", style);
        createCells(row, 1, "date", style);
        createCells(row, 2, "numberLessons", style);
        createCells(row, 3, "teacher", style);
    }

    private void writeCustomerData() {
        int rowCount = 2;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight((short) 14);

        for(Report report : listReport) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCells(row, columnCount++, report.getId(), style);
            createCells(row, columnCount++, report.getDates(), style);
            createCells(row, columnCount++, report.getNumberOfLessons(), style);
            createCells(row, columnCount++, report.getTeacher().getFirstName(), style);
        }
    }

    public void exportDataToExel(HttpServletResponse response) throws IOException {
        createHeaderRow();
        writeCustomerData();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}

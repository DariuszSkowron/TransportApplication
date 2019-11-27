package pl.skowrondariusz.TransportApplication.view;


import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import pl.skowrondariusz.TransportApplication.model.Transit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;

public class ExcelReportView extends AbstractXlsView {



    @Autowired
    ExportedFilesNameGenerator exportedFilesNameGenerator;



    @Override
    protected void buildExcelDocument(Map<String, Object> model, org.apache.poi.ss.usermodel.Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileFormat = "xls";
        response.setHeader("Content-Disposition", "attachment; filename=" + exportedFilesNameGenerator.exportedFileName(fileFormat) );
        List<Transit> transitList = (List<Transit>) model.get("transitList");
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Transit Data");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Transit Id");
        header.createCell(1).setCellValue("Transit source address");
        header.createCell(2).setCellValue("Transit destination address");
        header.createCell(3).setCellValue("Price");
        header.createCell(4).setCellValue("Distance");

        int rowNum = 1;
        for (Transit transit : transitList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(transit.getId());
            row.createCell(1).setCellValue(transit.getSourceAdress());
            row.createCell(2).setCellValue(transit.getDestinationAdress());
            row.createCell(3).setCellValue(transit.getPrice());
            row.createCell(4).setCellValue(transit.getDistance());
        }


        for (short i = sheet.getRow(0).getFirstCellNum(),
             end = sheet.getRow(0).getLastCellNum() ; i < end ; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}

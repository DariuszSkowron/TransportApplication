package pl.skowrondariusz.TransportApplication.view;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import pl.skowrondariusz.TransportApplication.model.Transit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelReportView extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, org.apache.poi.ss.usermodel.Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {


        response.setHeader("Content-Disposition", "attachment;filename= \"transits.xls\"");
        List<Transit> transitList = (List<Transit>) model.get("transitList");
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Transit Data");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Transit Id");
        header.createCell(1).setCellValue("Transit source adress");
        header.createCell(2).setCellValue("Transit destination adress");

        int rowNum = 1;
        for (Transit transit : transitList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(transit.getId());
            row.createCell(1).setCellValue(transit.getSourceAdress());
            row.createCell(2).setCellValue(transit.getDestinationAdress());
        }
    }
}

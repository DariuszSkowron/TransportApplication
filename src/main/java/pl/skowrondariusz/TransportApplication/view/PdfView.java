package pl.skowrondariusz.TransportApplication.view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.skowrondariusz.TransportApplication.model.Transit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PdfView extends AbstractPdfView {



    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"my-pdf-file.pdf\"");

        List<Transit> transits = (List<Transit>) model.get("transitList");
        document.add(new Paragraph("Generated Transits " + LocalDate.now()));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);
        Path path = Paths.get(ClassLoader.getSystemResource("static/logo.jpg").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(100);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);


        // write table header
        PdfPCell imageCell = new PdfPCell(img);
        document.add(img);

        cell.setPhrase(new Phrase("Transit Id", font));
        table.addCell(cell);



        cell.setPhrase(new Phrase("source adress", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("destination adress", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Distance", font));
        table.addCell(cell);


        for(Transit transit : transits){
            table.addCell(transit.getId().toString());
            table.addCell(transit.getSourceAdress());
            table.addCell(transit.getDestinationAdress());
            table.addCell(transit.getDistance().toString());
        }

        document.add(table);
    }
}
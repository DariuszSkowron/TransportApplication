package pl.skowrondariusz.TransportApplication.view;

import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ExportedFilesNameGenerator {


    public static String exportedFileName (String fileFormat) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        return "Transits-" + formattedDate + "." + fileFormat;
    }

}

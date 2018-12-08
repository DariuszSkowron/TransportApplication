package pl.skowrondariusz.TransportApplication.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateSerializer<T> extends StdSerializer<LocalDate> {

    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMMM, d", Locale.US);

    public DateSerializer(){
        this(null);
    }

    protected DateSerializer(Class<LocalDate> t){
        super(t);
    }
    @Override
    public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException{
        String valueParser = value.toString();
        String ending = null;
        if (valueParser.substring(valueParser.length() -1).equals("1")) {
            ending = "st";
        }
        else if (valueParser.substring(valueParser.length() -1).equals("2")) {
            ending = "nd";
        }
        else if (valueParser.substring(valueParser.length() -1).equals("3")) {
            ending = "rd";
        }
        else { ending ="th"; }

        jsonGenerator.writeString(formatter.format(value) + ending);
    }


}

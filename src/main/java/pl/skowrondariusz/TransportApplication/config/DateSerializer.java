package pl.skowrondariusz.TransportApplication.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateSerializer<T> extends StdSerializer<LocalDate> {

    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMMM, d", Locale.US);

    public DateSerializer() {
        this(null);
    }

    private DateSerializer(Class<LocalDate> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String valueParser = value.toString();
        String ending;
        switch (valueParser.substring(valueParser.length() - 1)) {
            case "1":
                ending = "st";
                break;
            case "2":
                ending = "nd";
                break;
            case "3":
                ending = "rd";
                break;
            default:
                ending = "th";
                break;
        }

        jsonGenerator.writeString(formatter.format(value) + ending);
    }

}

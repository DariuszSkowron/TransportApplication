//package pl.skowrondariusz.TransportApplication;
//
//import org.springframework.cglib.core.Converter;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//
//public final class LocalDateTimeConverter implements Converter<String , LocalDateTime> {
//
//    private final DateTimeFormatter formatter;
//
//    public DateConverter(String dateFormat){
//        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
//    }
//    @Override
//    public Date convert(String source){
//        if (source == null || source.isEmpty()){
//            return null;
//        }
//        return Date.parse(source, formatter);
//    }
//
//    @Override
//    public Object convert(Object o, Class aClass, Object o1) {
//        return null;
//    }
//}

//package pl.skowrondariusz.TransportApplication.config;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//
//import java.io.IOException;
//
//public class DateSerializer<T> extends StdSerializer<T> {
//
//    public DateSerializer(){
//        this(null);
//    }
//
//    protected DateSerializer(Class<T> t){
//        super(t);
//    }
//    @Override
//    public void serialize(T value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        jsonGenerator.writeString(value +);
//    }
//
//
//}

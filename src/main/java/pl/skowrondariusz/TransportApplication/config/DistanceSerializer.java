package pl.skowrondariusz.TransportApplication.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DistanceSerializer<T> extends StdSerializer<T>{

    public DistanceSerializer(){
        this(null);
    }

    protected DistanceSerializer(Class<T> t) {
        super(t);
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
//        String replaceText = (String) value;
//        replaceText = replaceText.replace(' ', '_');
        gen.writeString(value + " " + "km");
    }
}

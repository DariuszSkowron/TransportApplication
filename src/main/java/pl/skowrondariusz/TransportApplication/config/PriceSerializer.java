package pl.skowrondariusz.TransportApplication.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PriceSerializer<T> extends StdSerializer {

    public PriceSerializer(){
        this(null);
    }

    protected PriceSerializer(Class<T> t) {
        super(t);
    }


    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    jsonGenerator.writeString(o + " " +  "PLN");
    }
}

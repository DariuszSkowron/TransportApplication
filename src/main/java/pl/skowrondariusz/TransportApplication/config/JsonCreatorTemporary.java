package pl.skowrondariusz.TransportApplication.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.skowrondariusz.TransportApplication.model.Test;

public class JsonCreatorTemporary {

    public static String convertToJson(Test test) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(test);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

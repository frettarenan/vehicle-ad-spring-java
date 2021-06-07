package br.com.renanfretta.vehiclead.api.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperSpecialized extends ObjectMapper {

    public String writeValueAsStringNoException(Object value) {
        try {
            return writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "JsonProcessingException";
    }

}

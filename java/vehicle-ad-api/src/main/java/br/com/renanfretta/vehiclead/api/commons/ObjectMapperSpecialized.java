package br.com.renanfretta.vehiclead.api.commons;

import org.springframework.stereotype.Component;

@Component
public class ObjectMapperSpecialized extends com.fasterxml.jackson.databind.ObjectMapper {

    public String writeValueAsStringNoException(Object value) {
        try {
            return writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "JsonProcessingException";
    }

}

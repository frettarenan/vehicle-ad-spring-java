package br.com.renanfretta.vehiclead.api.configs;

import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.commons.ObjectMapperSpecialized;
import br.com.renanfretta.vehiclead.api.repositories.vehicledealer.VehicleDealerRepository;
import br.com.renanfretta.vehiclead.api.services.VehicleDealerService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@TestConfiguration
public class AutowiredTestsConfig {
    
    @Bean
    public OrikaMapper orikaMapper() {
        return new OrikaMapper();
    }

    @Bean
    public ObjectMapperSpecialized objectMapperSpecialized() {
        return new ObjectMapperSpecialized();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public MessagesProperty messagesProperty() {
        return new MessagesProperty(messageSource());
    }

}

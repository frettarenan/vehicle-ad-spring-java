package br.com.renanfretta.vehiclead.api.services;

import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.commons.ObjectMapperSpecialized;
import br.com.renanfretta.vehiclead.api.configs.AutowiredTestsConfig;
import br.com.renanfretta.vehiclead.api.configs.OrikaMapper;
import br.com.renanfretta.vehiclead.api.data.VehicleDealerData;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.exceptions.entity.ResourceNotFoundException;
import br.com.renanfretta.vehiclead.api.repositories.vehicledealer.VehicleDealerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AutowiredTestsConfig.class)
@DisplayName("Vehicle Dealer Service Tests")
public class VehicleDealerServiceTests {

    private final OrikaMapper orikaMapper;
    private final ObjectMapperSpecialized objectMapper;
    private final MessagesProperty messagesProperty;

    @Autowired
    public VehicleDealerServiceTests(OrikaMapper orikaMapper, ObjectMapperSpecialized objectMapper, MessagesProperty messagesProperty) {
        this.orikaMapper = orikaMapper;
        this.objectMapper = objectMapper;
        this.messagesProperty = messagesProperty;
    }

    @MockBean
    private VehicleDealerRepository repository;

    //@InjectMocks
    private VehicleDealerService vehicleDealerService;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.vehicleDealerService = new VehicleDealerService(repository, orikaMapper, objectMapper, messagesProperty);
    }

    @Nested
    @DisplayName("Method: findAll")
    class findAll {

        @Test
        @DisplayName("Successfully")
        public void successfully() {
            Mockito.doReturn(VehicleDealerData.getListVehicleDealerOutputDTO01()).when(repository).findAll();

            List<VehicleDealerOutputDTO> list = vehicleDealerService.findAll();
            Assertions.assertTrue(list.size() == 1);
        }

    }

    @Nested
    @DisplayName("Method: findById")
    class findById {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws ResourceNotFoundException {
            long id = 1L;
            Mockito.doReturn(VehicleDealerData.getOptionalVehicleDealer01()).when(repository).findById(id);

            VehicleDealerOutputDTO dto = vehicleDealerService.findById(id);
            Assertions.assertTrue(dto.getId() == id);
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() {
            long id = 1L;
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                vehicleDealerService.findById(1L);
            });
        }

    }

}

package br.com.renanfretta.vehiclead.api.services;

import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.commons.ObjectMapperSpecialized;
import br.com.renanfretta.vehiclead.api.configs.AutowiredTestsConfig;
import br.com.renanfretta.vehiclead.api.configs.OrikaMapper;
import br.com.renanfretta.vehiclead.api.data.VehicleDealerData;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.input.VehicleDealerInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehicledealer.output.VehicleDealerOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleDealer;
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
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AutowiredTestsConfig.class)
@DisplayName("Vehicle Dealer Service Tests")
public class VehicleDealerServiceUnitTests {

    private final OrikaMapper orikaMapper;
    private final ObjectMapperSpecialized objectMapper;
    private final MessagesProperty messagesProperty;

    @Autowired
    public VehicleDealerServiceUnitTests(OrikaMapper orikaMapper, ObjectMapperSpecialized objectMapper, MessagesProperty messagesProperty) {
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

    @Nested
    @DisplayName("Method: save")
    class save {

        @Test
        @DisplayName("Successfully")
        public void successfully() {
            Optional<VehicleDealer> optionalVehicleDealer = VehicleDealerData.getOptionalVehicleDealer01();
            long id = optionalVehicleDealer.get().getId();

            Mockito.doReturn(VehicleDealerData.getVehicleDealer01()).when(repository).save(Mockito.any());
            Mockito.doReturn(optionalVehicleDealer).when(repository).findById(id);

            VehicleDealerOutputDTO dto = vehicleDealerService.save(VehicleDealerData.getVehicleDealerInputDTO01());
            Assertions.assertTrue(dto.getId() == id);
        }

    }

    @Nested
    @DisplayName("Method: updateAll")
    class updateAll {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws ResourceNotFoundException {
            long id = 1L;
            VehicleDealerInputDTO inputDTO = VehicleDealerData.getVehicleDealerInputDTO02();

            Mockito.doReturn(VehicleDealerData.getOptionalVehicleDealer02()).when(repository).findById(id);

            VehicleDealerOutputDTO dto =  vehicleDealerService.updateAll(id, inputDTO);
            Assertions.assertTrue(dto.getName() == inputDTO.getName());
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() {
            long id = 1L;
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                vehicleDealerService.updateAll(id, VehicleDealerData.getVehicleDealerInputDTO01());
            });
        }

    }

    @Nested
    @DisplayName("Method: partialUpdate")
    class partialUpdate {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws ResourceNotFoundException {
            long id = 1L;
            VehicleDealerInputDTO inputDTO = VehicleDealerData.getVehicleDealerInputDTO02();

            Mockito.doReturn(VehicleDealerData.getOptionalVehicleDealer02()).when(repository).findById(id);

            VehicleDealerOutputDTO dto =  vehicleDealerService.partialUpdate(id, inputDTO);
            Assertions.assertTrue(dto.getName() == inputDTO.getName());
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() {
            long id = 1L;
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                vehicleDealerService.partialUpdate(id, VehicleDealerData.getVehicleDealerInputDTO01());
            });
        }

    }

    @Nested
    @DisplayName("Method: deleteById")
    class deleteById {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws ResourceNotFoundException {
            long id = 1L;

            Mockito.doReturn(VehicleDealerData.getOptionalVehicleDealer02()).when(repository).findById(id);

            VehicleDealerOutputDTO dto =  vehicleDealerService.deleteById(id);
            Assertions.assertTrue(dto.getId() == id);
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() {
            long id = 1L;
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                vehicleDealerService.deleteById(id);
            });
        }

    }

}

package br.com.renanfretta.vehiclead.api.services;

import br.com.renanfretta.vehiclead.api.commons.MessagesProperty;
import br.com.renanfretta.vehiclead.api.commons.ObjectMapperSpecialized;
import br.com.renanfretta.vehiclead.api.configs.AutowiredTestsConfig;
import br.com.renanfretta.vehiclead.api.configs.OrikaMapper;
import br.com.renanfretta.vehiclead.api.data.VehicleAdData;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.input.VehicleAdUpdateInputDTO;
import br.com.renanfretta.vehiclead.api.dtos.vehiclead.output.VehicleAdOutputDTO;
import br.com.renanfretta.vehiclead.api.entities.VehicleAd;
import br.com.renanfretta.vehiclead.api.exceptions.entity.ResourceNotFoundException;
import br.com.renanfretta.vehiclead.api.exceptions.vehiclead.VehicleAdAlreadyPublishedException;
import br.com.renanfretta.vehiclead.api.exceptions.vehicledealer.VehicleDealerTierLimitExceededException;
import br.com.renanfretta.vehiclead.api.repositories.vehiclead.VehicleAdRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AutowiredTestsConfig.class)
@DisplayName("Vehicle Dealer Service Tests")
public class VehicleAdServiceUnitTests {

    private final OrikaMapper orikaMapper;
    private final ObjectMapperSpecialized objectMapper;
    private final MessagesProperty messagesProperty;

    @Autowired
    public VehicleAdServiceUnitTests(OrikaMapper orikaMapper, ObjectMapperSpecialized objectMapper, MessagesProperty messagesProperty) {
        this.orikaMapper = orikaMapper;
        this.objectMapper = objectMapper;
        this.messagesProperty = messagesProperty;
    }

    @MockBean
    private VehicleAdRepository repository;

    //@InjectMocks
    private VehicleAdService vehicleAdService;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.vehicleAdService = new VehicleAdService(repository, orikaMapper, objectMapper, messagesProperty);
    }

    @Nested
    @DisplayName("Method: findByVehicleDealerAndState")
    class findAll {

        @Test
        @DisplayName("Successfully")
        public void successfully() {
            Long vehicleDealerId = 1L;
            Short vehicleAdStateId = null;

            Mockito.doReturn(VehicleAdData.getListVehicleAd01()).when(repository).findByVehicleDealerAndState(vehicleDealerId, vehicleAdStateId);

            List<VehicleAdOutputDTO> list = vehicleAdService.findByVehicleDealerAndState(vehicleDealerId, vehicleAdStateId);
            Assertions.assertTrue(list.size() == 1);
        }

    }

    @Nested
    @DisplayName("Method: save")
    class save {

        @Test
        @DisplayName("Successfully")
        public void successfully() {
            Optional<VehicleAd> optionalVehicleDealer = VehicleAdData.getOptionalVehicleAd01();
            long id = optionalVehicleDealer.get().getId();

            Mockito.doReturn(VehicleAdData.getVehicleAd01()).when(repository).save(Mockito.any());
            Mockito.doReturn(optionalVehicleDealer).when(repository).findById(id);

            VehicleAdOutputDTO dto = vehicleAdService.save(VehicleAdData.getVehicleAdInsertInputDTO01());
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
            VehicleAdUpdateInputDTO inputDTO = VehicleAdData.getVehicleAdUpdateInputDTO02();

            Mockito.doReturn(VehicleAdData.getOptionalVehicleAd02()).when(repository).findById(id);

            VehicleAdOutputDTO dto = vehicleAdService.updateAll(id, inputDTO);
            Assertions.assertTrue(dto.getBrand() == inputDTO.getBrand());
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() {
            long id = 1L;
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                vehicleAdService.updateAll(id, VehicleAdData.getVehicleAdUpdateInputDTO02());
            });
        }

    }

    @Nested
    @DisplayName("Method: publish")
    class publish {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws ResourceNotFoundException, VehicleAdAlreadyPublishedException, VehicleDealerTierLimitExceededException {
            long id = 1L;

            Mockito.doReturn(VehicleAdData.getOptionalVehicleAd01()).when(repository).findById(id);
            Mockito.doReturn(new ArrayList<VehicleAd>()).when(repository).findByVehicleDealerAndState(Mockito.any(), Mockito.any());

            VehicleAdOutputDTO dto = vehicleAdService.publish(id, true);
            Assertions.assertTrue(dto.getId() == id);
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() {
            long id = 1L;
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                vehicleAdService.publish(id, true);
            });
        }

        @Test
        @DisplayName("Vehicle Ad Already Published Exception")
        public void vehicleAdAlreadyPublishedException() {
            long id = 1L;

            Mockito.doReturn(VehicleAdData.getOptionalVehicleAd02()).when(repository).findById(id);

            Assertions.assertThrows(VehicleAdAlreadyPublishedException.class, () -> {
                vehicleAdService.publish(id, true);
            });
        }

        @Test
        @DisplayName("Vehicle Dealer Tier Limit Exceeded Exception")
        public void vehicleDealerTierLimitExceededException() {
            long id = 1L;

            Mockito.doReturn(VehicleAdData.getOptionalVehicleAd01()).when(repository).findById(id);
            Mockito.doReturn(VehicleAdData.getListVehicleAd01()).when(repository).findByVehicleDealerAndState(Mockito.any(), Mockito.any());

            Assertions.assertThrows(VehicleDealerTierLimitExceededException.class, () -> {
                vehicleAdService.publish(id, true);
            });
        }

    }

    @Nested
    @DisplayName("Method: unpublish")
    class unpublish {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws ResourceNotFoundException {
            long id = 1L;

            Mockito.doReturn(VehicleAdData.getOptionalVehicleAd01()).when(repository).findById(id);

            VehicleAdOutputDTO dto = vehicleAdService.unpublish(id);
            Assertions.assertTrue(dto.getId() == id);
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() {
            long id = 1L;
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                vehicleAdService.unpublish(id);
            });
        }

    }

}

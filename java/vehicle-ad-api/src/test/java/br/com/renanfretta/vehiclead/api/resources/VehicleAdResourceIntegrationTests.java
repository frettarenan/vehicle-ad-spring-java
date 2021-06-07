package br.com.renanfretta.vehiclead.api.resources;

import br.com.renanfretta.vehiclead.api.data.VehicleAdData;
import br.com.renanfretta.vehiclead.api.entities.VehicleAd;
import br.com.renanfretta.vehiclead.api.repositories.vehiclead.VehicleAdRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VehicleAdResourceIntegrationTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private VehicleAdRepository repository;

    @Autowired
    public VehicleAdResourceIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Nested
    @DisplayName("Method: findByVehicleDealerAndState")
    class findAll {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws Exception {
            Long vehicleDealerId = 1L;
            Short vehicleAdStateId = null;

            BDDMockito.when(repository.findByVehicleDealerAndState(vehicleDealerId, vehicleAdStateId)).thenReturn(VehicleAdData.getListVehicleAd01());

            mockMvc.perform(get("/vehicle-ad?vehicleDealerId=" + vehicleDealerId))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.length()", is(1)))
                    .andExpect(jsonPath("$.[0].id").value(1))
                    .andExpect(jsonPath("$.[0].brand").value("Honda"));
        }

    }

    @Nested
    @DisplayName("Method: save")
    class save {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws Exception {
            Optional<VehicleAd> optionalVehicleDealer = VehicleAdData.getOptionalVehicleAd01();

            BDDMockito.when(repository.save(BDDMockito.any())).thenReturn(VehicleAdData.getVehicleAd01());
            BDDMockito.when(repository.findById(BDDMockito.any())).thenReturn(optionalVehicleDealer);

            mockMvc.perform(post("/vehicle-ad").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(VehicleAdData.getVehicleAdInsertInputDTO01())))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.brand").value("Honda"));
        }

    }

    @Nested
    @DisplayName("Method: updateAll")
    class updateAll {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws Exception {
            BDDMockito.when(repository.findById(BDDMockito.any())).thenReturn(VehicleAdData.getOptionalVehicleAd02());

            mockMvc.perform(put("/vehicle-ad/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(VehicleAdData.getVehicleAdUpdateInputDTO02())))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.brand").value("Jeep"));
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() throws Exception {
            mockMvc.perform(put("/vehicle-ad/99999").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(VehicleAdData.getVehicleAdUpdateInputDTO02())))
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    @DisplayName("Method: publish")
    class publish {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws Exception {
            BDDMockito.when(repository.findById(BDDMockito.any())).thenReturn(VehicleAdData.getOptionalVehicleAd01());
            BDDMockito.when(repository.findByVehicleDealerAndState(BDDMockito.any(), BDDMockito.any())).thenReturn(new ArrayList<VehicleAd>());

            mockMvc.perform(patch("/vehicle-ad/1/publish").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(VehicleAdData.getVehicleAdInsertInputDTO01())))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1));
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() throws Exception {
            mockMvc.perform(patch("/vehicle-ad/1/publish").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(VehicleAdData.getVehicleAdInsertInputDTO01())))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Vehicle Ad Already Published Exception")
        public void vehicleAdAlreadyPublishedException() throws Exception {
            BDDMockito.when(repository.findById(BDDMockito.any())).thenReturn(VehicleAdData.getOptionalVehicleAd02());

            mockMvc.perform(patch("/vehicle-ad/1/publish").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(VehicleAdData.getVehicleAdInsertInputDTO01())))
                    .andExpect(status().isUnprocessableEntity());
        }

        @Test
        @DisplayName("Vehicle Dealer Tier Limit Exceeded Exception")
        public void vehicleDealerTierLimitExceededException() throws Exception {
            BDDMockito.when(repository.findById(BDDMockito.any())).thenReturn(VehicleAdData.getOptionalVehicleAd01());
            BDDMockito.when(repository.findByVehicleDealerAndState(BDDMockito.any(), BDDMockito.any())).thenReturn(VehicleAdData.getListVehicleAd01());

            mockMvc.perform(patch("/vehicle-ad/1/publish").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(VehicleAdData.getVehicleAdInsertInputDTO01())))
                    .andExpect(status().isUnprocessableEntity());
        }

    }

    @Nested
    @DisplayName("Method: unpublish")
    class unpublish {

        @Test
        @DisplayName("Successfully")
        public void successfully() throws Exception {
            BDDMockito.when(repository.findById(BDDMockito.any())).thenReturn(VehicleAdData.getOptionalVehicleAd01());

            mockMvc.perform(patch("/vehicle-ad/1/unpublish"))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Resource Not Found Exception")
        public void resourceNotFoundException() throws Exception {
            mockMvc.perform(patch("/vehicle-ad/99999/unpublish"))
                    .andExpect(status().isNotFound());
        }

    }

}

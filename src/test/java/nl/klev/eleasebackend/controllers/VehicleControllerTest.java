package nl.klev.eleasebackend.controllers;

import nl.klev.eleasebackend.dtos.VehicleDto;
import nl.klev.eleasebackend.dtos.VehicleInputDto;
import nl.klev.eleasebackend.filter.JwtRequestFilter;
import nl.klev.eleasebackend.models.Vehicle;
import nl.klev.eleasebackend.services.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    private VehicleService vehicleService;

    Vehicle vehicle1;
    Vehicle vehicle2;

    VehicleDto vehicleDto1;
    VehicleDto vehicleDto2;
    VehicleInputDto vehicleInputDto1;


    @BeforeEach
    void setUp() {
        vehicle1 = new Vehicle();

        vehicle1.setVehicleId(3L);
        vehicle1.setType(Vehicle.VehicleType.valueOf("ELECTRIC_CAR"));
        vehicle1.setBrand("Audi");

        vehicle2 = new Vehicle();

        vehicle2.setVehicleId(2L);
        vehicle2.setType(Vehicle.VehicleType.valueOf("ELECTRIC_SCOOTER"));
        vehicle2.setBrand("Zip");

        vehicleDto1 = new VehicleDto();

        vehicleDto1.setVehicleId(1L);
        vehicleDto1.setType(Vehicle.VehicleType.valueOf("ELECTRIC_CAR"));
        vehicleDto1.setBrand("Audi");

        vehicleDto2 = new VehicleDto();

        vehicleDto2.setVehicleId(2L);
        vehicleDto2.setType(Vehicle.VehicleType.valueOf("ELECTRIC_SCOOTER"));
        vehicleDto2.setBrand("Zip");


        vehicleInputDto1 = new VehicleInputDto();

        vehicleInputDto1.setType(Vehicle.VehicleType.valueOf("ELECTRIC_CAR"));
        vehicleInputDto1.setBrand("Audi");
    }

    @Test
    void shouldReturnAllVehicles() throws Exception {
        when(vehicleService.getVehicles()).thenReturn(List.of(vehicleDto1, vehicleDto2));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].type").value("ELECTRIC_CAR"))
        ;
    }

    @Test
    void shouldReturnRightVehicle() throws Exception {
        when(vehicleService.getVehicleById(anyLong())).thenReturn(vehicleDto1);

        this.mockMvc
                .perform(get("/vehicles/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleId").value("1"))
        ;
    }

    @Test
    void shouldDeleteVehicle() throws Exception {
        this.mockMvc
                .perform(delete("/vehicles/3"))
                .andExpect(status().isNoContent())
        ;
    }
}
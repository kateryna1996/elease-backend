package nl.klev.eleasebackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.klev.eleasebackend.dtos.GarageDto;
import nl.klev.eleasebackend.dtos.GarageInputDto;
import nl.klev.eleasebackend.filter.JwtRequestFilter;
import nl.klev.eleasebackend.models.Garage;
import nl.klev.eleasebackend.services.GarageService;
import nl.klev.eleasebackend.utilities.GarageTransform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(GarageController.class)
class GarageControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    private GarageService garageService;

    Garage garage1;
    Garage garage2;
    Garage garage3;

    GarageDto garageDto1;
    GarageDto garageDto2;
    GarageDto garageDto3;

    GarageInputDto garageInputDto;

    @BeforeEach
    void setUp() {
        garage1 = new Garage();

        garage1.setGarageName("Gron27");
        garage1.setCountry(Garage.Country.valueOf("NETHERLANDS"));
        garage1.setZipCode("4543MG");

        garage2 = new Garage();

        garage2.setGarageName("Gent05");
        garage2.setCountry(Garage.Country.valueOf("BELGIUM"));
        garage2.setZipCode("8215KF");

        garageDto1 = new GarageDto();
        garageDto1 = GarageTransform.toGarageDto(garage1);

        garageDto2 = new GarageDto();
        garageDto2 = GarageTransform.toGarageDto(garage2);

        garageInputDto = new GarageInputDto();

        garageInputDto.setGarageName("Alm89");
        garageInputDto.setCountry(Garage.Country.valueOf("NETHERLANDS"));
        garageInputDto.setZipCode("7113KS");

        garage3 = new Garage();
        garage3 = GarageTransform.toGarage(garageInputDto);

        garageDto3 = new GarageDto();

        garageDto3.setGarageName("Alm89");
        garageDto3.setCountry(Garage.Country.valueOf("NETHERLANDS"));
        garageDto3.setZipCode("7113KS");
    }

    @Test
    void shouldReturnAllGarages() throws Exception {
        when(garageService.getGarageList()).thenReturn(List.of(garageDto1, garageDto2));

        this.mockMvc
                .perform(get("/garages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].garageName").value("Gent05"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturnCorrectGarage() throws Exception {
        String name = "Gent05";
        when(garageService.getGarageByName(name)).thenReturn(garageDto2);

        this.mockMvc
                .perform(get("/garages/Gent05"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.garageName").value("Gent05"))
                .andExpect(jsonPath("$.country").value("BELGIUM"))
                .andExpect(jsonPath("$.zipCode").value("8215KF"))
        ;
    }

    @Test
    void shouldCreateGarage() throws Exception {
        when(garageService.createGarage(any(GarageInputDto.class))).thenReturn(garageDto3);

        this.mockMvc
                .perform(post("/garages")
                        .content(asJsonString(garageDto3))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
        ;
    }

    @Test
    void shouldUpdateGarage() throws Exception {
        garageService.updateGarage("Gron27", garageInputDto);

        this.mockMvc
                .perform(put("/garages/Gron27")
                        .content(asJsonString(garageInputDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Done!"))
        ;
    }

    @Test
    void deleteInstruction() throws Exception {
        this.mockMvc
                .perform(delete("/garages/Gron27"))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
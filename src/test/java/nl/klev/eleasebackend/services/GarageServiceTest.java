package nl.klev.eleasebackend.services;

import nl.klev.eleasebackend.dtos.GarageDto;
import nl.klev.eleasebackend.dtos.GarageInputDto;
import nl.klev.eleasebackend.models.Garage;
import nl.klev.eleasebackend.repositories.GarageRepository;
import nl.klev.eleasebackend.utilities.GarageTransform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GarageServiceTest {

    @Mock
    GarageRepository garageRepository;

    @InjectMocks
    GarageService garageService;

    @Captor
    ArgumentCaptor<Garage> captor;

    Garage garage1;
    Garage garage2;
    Garage garage3;

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

        garageInputDto = new GarageInputDto();

        garageInputDto.setCountry(Garage.Country.valueOf("BELGIUM"));
        garageInputDto.setZipCode("9072OX");

        garage3 = GarageTransform.toGarage(garageInputDto);
    }

    @Test
    void shouldGetGarageList() {
        when(garageRepository.findAll()).thenReturn(List.of(garage1, garage2));

        List<GarageDto> garageDtoList = garageService.getGarageList();

        assertEquals(2, garageDtoList.size());
        assertEquals(garage2.getGarageName(), garageDtoList.get(1).getGarageName());
    }


    @Test
    void shouldReturnGarageByName() {
        when(garageRepository.findById(anyString())).thenReturn(Optional.ofNullable(garage1));

        GarageDto garageDto = garageService.getGarageByName("Gron27");

        assertEquals(garage1.getCountry(), garageDto.getCountry());
    }

    @Test
    void checksIfGarageWithTheIdExists() {
        when(garageRepository.findById(anyString())).thenReturn(Optional.ofNullable(garage1));
        Boolean exists = garageService.garageExists("Gron27");

        assertEquals(true, exists);

    }

    @Test
    void shouldDeleteGarageByName() {
        String name = garage1.getGarageName();

        when(garageRepository.findById(name)).thenReturn(Optional.ofNullable(garage1));

        garageService.deleteGarageByName(name);

        verify(garageRepository).deleteById(name);
    }

    @Test
    void shouldUpdateGarage() {
        String name = "Gron27";

        when(garageRepository.findById(anyString())).thenReturn(Optional.of(garage1));
        when(garageRepository.save(any())).thenReturn(garage3);
         garageService.updateGarage(name, garageInputDto);
         verify(garageRepository).save(captor.capture());

         Garage captured = captor.getValue();

        assertEquals("Gron27", captured.getGarageName());
        assertEquals("9072OX", captured.getZipCode());
        assertEquals("BELGIUM", captured.getCountry().toString());
    }
}
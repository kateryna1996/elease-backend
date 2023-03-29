package nl.klev.eleasebackend.services;

import nl.klev.eleasebackend.dtos.VehicleDto;
import nl.klev.eleasebackend.dtos.VehicleInputDto;
import nl.klev.eleasebackend.models.Vehicle;
import nl.klev.eleasebackend.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VehicleServiceTest {

    @Mock
    VehicleRepository vehicleRepository;

    @InjectMocks
    VehicleService vehicleService;

    Vehicle vehicle1;
    Vehicle vehicle2;
    VehicleDto vehicleDto1;
    VehicleDto vehicleDto2;
    VehicleInputDto vehicleDto;

    @BeforeEach
    void setUp() {
        vehicle1 = new Vehicle();

        vehicle1.setVehicleId(1L);
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

        vehicleDto = new VehicleInputDto();

        vehicleDto.setType(Vehicle.VehicleType.valueOf("ELECTRIC_CAR"));
        vehicleDto.setBrand("Audi");
    }

    @Test
    void shouldCreateNewVehicle() {
        when(vehicleRepository.save(ArgumentMatchers.any(Vehicle.class))).thenReturn(vehicle1);

        VehicleDto actual = vehicleService.createVehicle(vehicleDto);

        assertEquals(vehicle1.getType(), actual.getType());
        assertEquals(vehicle1.getBrand(), actual.getBrand());
    }

    @Test
    void shouldReturnAllVehicles() {
        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle1, vehicle2));

        List<VehicleDto> vehicleDtoList = vehicleService.getVehicles();

        assertEquals(2, vehicleDtoList.size());assertEquals("Audi", vehicleDtoList.get(0).getBrand());
    }

    @Test
    void checksIfVehicleExists() {
        when(vehicleRepository.existsById(anyLong())).thenReturn(true);
        Boolean exists = vehicleService.vehicleExists(1L);

        assertEquals(true, exists);
    }

    @Test
    void shouldReturnRightVehicle() {
        when(vehicleRepository.existsById(anyLong())).thenReturn(true);
        when(vehicleRepository.findById(anyLong())).thenReturn(Optional.of(vehicle1));

        VehicleDto vehicleDtoToCheck = vehicleService.getVehicleById(1L);

        assertEquals(vehicle1.getVehicleId(), vehicleDtoToCheck.getVehicleId());
        assertEquals(vehicle1.getType(), vehicleDtoToCheck.getType());
        assertEquals(vehicle1.getBrand(), vehicleDtoToCheck.getBrand());
    }

    @Test
    void shouldDeleteRightVehicle() {
        when(vehicleRepository.existsById(anyLong())).thenReturn(true);
        vehicleRepository.deleteById(1L);

        verify(vehicleRepository).deleteById(1L);
    }

}
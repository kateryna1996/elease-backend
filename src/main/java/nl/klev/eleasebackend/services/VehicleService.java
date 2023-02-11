package nl.klev.eleasebackend.services;

import nl.klev.eleasebackend.dtos.VehicleDto;
import nl.klev.eleasebackend.dtos.VehicleInputDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.models.Vehicle;
import nl.klev.eleasebackend.repositories.GarageRepository;
import nl.klev.eleasebackend.repositories.VehicleRepository;
import nl.klev.eleasebackend.utilities.VehicleTransform;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final GarageRepository garageRepository;

    public VehicleService(VehicleRepository vehicleRepository, GarageRepository garageRepository) {
        this.vehicleRepository = vehicleRepository;
        this.garageRepository = garageRepository;
    }

    public VehicleDto createVehicle(VehicleInputDto vehicleInputDto) {
        VehicleDto createdVehicleDto = new VehicleDto();

        Vehicle createdVehicle = VehicleTransform.toVehicle(vehicleInputDto);

        vehicleRepository.save(createdVehicle);
        createdVehicleDto = VehicleTransform.toVehicleDto(createdVehicle);

        return createdVehicleDto;
    }

    public List<VehicleDto> getVehicles() {
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        List<Vehicle> vehicleList = vehicleRepository.findAll();

        for (Vehicle v : vehicleList) {
            vehicleDtoList.add(VehicleTransform.toVehicleDto(v));
        }
        if (vehicleList.isEmpty()) {
            throw new RecordNotFoundException("The list is empty!");
        }
        return vehicleDtoList;
    }

    public VehicleDto getVehicleById(Long id) {
        VehicleDto vehicleDto = new VehicleDto();
        if(vehicleExists(id)){
            Vehicle foundVehicle = vehicleRepository.findById(id).get();
            vehicleDto = VehicleTransform.toVehicleDto(foundVehicle);

        }else {
            throw new RecordNotFoundException("The vehicle with the id " + id + " cannot be found!");
        }
        return vehicleDto;
    }

    public void updateVehicle(Long id, VehicleInputDto vehicleInputDto) {
        if(vehicleExists(id)){
            Vehicle updatedVehicle = vehicleRepository.findById(id).get();
            Vehicle vehicleToSet = VehicleTransform.toVehicle(vehicleInputDto);
            vehicleToSet.setVehicleId(updatedVehicle.getVehicleId());
            vehicleRepository.save(vehicleToSet);
        } else {
            throw new RecordNotFoundException("The vehicle could not be updated as it was not found");
        }
    }

    public void deleteVehicle(Long id){
        if(vehicleExists(id)) {
            vehicleRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("The vehicle with the id " + id + " cannot be found!");
        }
    }

    public boolean vehicleExists(Long id){
       return vehicleRepository.existsById(id);
    }

    public void assignGarageToVehicles(Long id, String garageId) {
        var foundGarage = garageRepository.findById(garageId);
        var foundVehicle = vehicleRepository.findById(id);

        if(foundGarage.isPresent() && foundGarage.isPresent()){
            var vehicle = foundVehicle.get();
            vehicle.setGarage(foundGarage.get());
            vehicleRepository.save(vehicle);
        } else if(foundGarage.isEmpty()) {
            throw new RecordNotFoundException("Garage with the name has not been found!");
        } else {
            throw new RecordNotFoundException("Vehicle with the id has not been found!");
        }

    }
}

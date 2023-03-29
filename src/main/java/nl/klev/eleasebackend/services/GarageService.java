package nl.klev.eleasebackend.services;

import nl.klev.eleasebackend.dtos.GarageDto;
import nl.klev.eleasebackend.dtos.GarageInputDto;
import nl.klev.eleasebackend.dtos.VehicleDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.models.Garage;
import nl.klev.eleasebackend.models.Vehicle;
import nl.klev.eleasebackend.repositories.GarageRepository;
import nl.klev.eleasebackend.utilities.GarageTransform;
import nl.klev.eleasebackend.utilities.VehicleTransform;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GarageService {

    private final GarageRepository garageRepository;

    public GarageService(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    public GarageDto createGarage(GarageInputDto garageInputDto) {
        GarageDto garageDto = new GarageDto();
        Garage createdGarage = GarageTransform.toGarage(garageInputDto);
        if (garageExists(garageInputDto.getGarageName())) {
            throw new RecordNotFoundException("The garage with this name already exists!");
        } else {
            garageRepository.save(createdGarage);
            garageDto = GarageTransform.toGarageDto(createdGarage);
            return garageDto;
        }
    }

    public List<GarageDto> getGarageList() {
        List<GarageDto> garageDtoList = new ArrayList<>();
        List<Garage> garageList = garageRepository.findAll();
        for (Garage g : garageList) {
            garageDtoList.add(GarageTransform.toGarageDto(g));
        }
        if (garageList.isEmpty()) {
            throw new RecordNotFoundException("The list is empty!");
        }
        return garageDtoList;
    }

    public GarageDto getGarageByName(String name) {
        GarageDto garageDto ;
        Optional<Garage> foundGarage = garageRepository.findById(name);
        if (foundGarage.isPresent()) {
            garageDto = GarageTransform.toGarageDto(foundGarage.get());
        } else {
            throw new RecordNotFoundException("The garage with the name " + name + " cannot be found!");
        }
        return garageDto;
    }

    public List<VehicleDto> getVehiclesByGarageName(String name) {
        Optional<Garage> foundGarage = garageRepository.findById(name);
        if (foundGarage.isEmpty()) {
            throw new RecordNotFoundException("The garage with the name " + name + " cannot be found!");
        }
        List<Vehicle> list = foundGarage.get().getVehicles();
        List<VehicleDto> vehiclesList = new ArrayList<>();
        if (list.isEmpty()) {
            throw new RecordNotFoundException("The list is empty!");
        } else {
            for (Vehicle v : list) {
                vehiclesList.add(VehicleTransform.toVehicleDto(v));
            }
            return vehiclesList;
        }
    }

    public void deleteGarageByName(String name) {
        garageRepository.deleteById(name);
    }

    public void updateGarage(String name, GarageInputDto garageInputDto) {
        Optional<Garage> garage = garageRepository.findById(name);
        if (garage.isPresent()) {
            Garage updatedGarage = garage.get();
            Garage garageToSet = GarageTransform.toGarage(garageInputDto);
            garageToSet.setGarageName(updatedGarage.getGarageName());
            garageRepository.save(garageToSet);
        } else {
            throw new RecordNotFoundException("The garage was not found");
        }
    }

    public boolean garageExists(String name) {
        Optional<Garage> foundGarage = garageRepository.findById(name);
        return foundGarage.isPresent();
    }
}

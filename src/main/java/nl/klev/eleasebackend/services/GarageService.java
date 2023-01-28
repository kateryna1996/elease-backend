package nl.klev.eleasebackend.services;

import nl.klev.eleasebackend.dtos.GarageDto;
import nl.klev.eleasebackend.dtos.GarageInputDto;
import nl.klev.eleasebackend.dtos.MembershipDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.models.Garage;
import nl.klev.eleasebackend.repositories.GarageRepository;
import nl.klev.eleasebackend.utilities.GarageTransform;
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

        garageRepository.save(createdGarage);
        garageDto = GarageTransform.toGarageDto(createdGarage);

        return garageDto;
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
        GarageDto garageDto = new GarageDto();

        Optional<Garage> foundGarage = garageRepository.findById(name);

        if(foundGarage.isPresent()){
            garageDto = GarageTransform.toGarageDto(foundGarage.get());
        } else {
            throw new RecordNotFoundException("The garage with the name " + name + " cannot be found!");
        }
        return garageDto;
    }
}

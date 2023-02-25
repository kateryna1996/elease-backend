package nl.klev.eleasebackend.controllers;

import nl.klev.eleasebackend.dtos.GarageDto;
import nl.klev.eleasebackend.dtos.GarageInputDto;
import nl.klev.eleasebackend.dtos.VehicleDto;
import nl.klev.eleasebackend.services.GarageService;
import nl.klev.eleasebackend.utilities.ErrorReport;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/garages")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }


    @PostMapping("")
    public ResponseEntity<Object> createGarage(@Valid @RequestBody GarageInputDto garageInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            GarageDto garageDto = garageService.createGarage(garageInputDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/garages/" + garageDto.getGarageName()).toUriString());

            return ResponseEntity.created(uri).body(garageDto);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<GarageDto>> getGarageList() {
        List<GarageDto> garageDtoList = garageService.getGarageList();

        return ResponseEntity.ok().body(garageDtoList);
    }

    @GetMapping("/{name}")
    public ResponseEntity<GarageDto> getGarageByName(@PathVariable("name") String name) {
        GarageDto garageDto = garageService.getGarageByName(name);

        return ResponseEntity.ok().body(garageDto);
    }

    @GetMapping("/{name}/vehicles")
    public ResponseEntity<List<VehicleDto>> getGarageWithVehicles(@PathVariable("name") String name) {
        List<VehicleDto> vehiclesList = garageService.getVehiclesByGarageName(name);
        return ResponseEntity.ok().body(vehiclesList);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Object> updateGarage(@PathVariable("name") String name, @Valid @RequestBody GarageInputDto garageInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            garageService.updateGarage(name, garageInputDto);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteGarageByName(@PathVariable("name") String name) {
        garageService.deleteGarageByName(name);
        return ResponseEntity.noContent().build();
    }

}

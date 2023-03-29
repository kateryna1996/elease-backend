package nl.klev.eleasebackend.controllers;

import nl.klev.eleasebackend.dtos.VehicleDto;
import nl.klev.eleasebackend.dtos.VehicleInputDto;
import nl.klev.eleasebackend.services.VehicleService;
import nl.klev.eleasebackend.utilities.ErrorReport;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;


    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addVehicle(@Valid @RequestBody VehicleInputDto vehicleInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            VehicleDto vehicleDto = vehicleService.createVehicle(vehicleInputDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/vehicles/" + vehicleDto.getVehicleId()).toUriString());

            return ResponseEntity.created(uri).body(vehicleDto);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<VehicleDto>> getVehicles() {
        List<VehicleDto> vehicleDtoList = vehicleService.getVehicles();
        return ResponseEntity.ok().body(vehicleDtoList);
    }

    @GetMapping("/available")
    public ResponseEntity<List<VehicleDto>> getAvailableVehicles() {
        List<VehicleDto> availableVehicles = vehicleService.getAvailableVehicles();
        return ResponseEntity.ok().body(availableVehicles);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable("vehicleId") Long id) {
        VehicleDto vehicleDto = vehicleService.getVehicleById(id);
        return ResponseEntity.ok().body(vehicleDto);
    }

    @GetMapping("/brands/{brand}")
    public ResponseEntity<List<VehicleDto>> getVehicleByBrand(@PathVariable("brand") String brand) {
        List<VehicleDto> vehicleDtoList = vehicleService.getVehiclesByBrand(brand);
        return ResponseEntity.ok().body(vehicleDtoList);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<Object> updateVehicle(@PathVariable("vehicleId") Long id, @Valid @RequestBody VehicleInputDto vehicleInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            vehicleService.updateVehicle(id, vehicleInputDto);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("vehicleId") Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{vehicleId}/{garageName}")
    public ResponseEntity<?> assignGarageToVehicle(@PathVariable("vehicleId") Long vehicleId, @PathVariable("garageName") String garageName) {
        vehicleService.assignGarageToVehicles(vehicleId, garageName);
        return ResponseEntity.noContent().build();
    }
}

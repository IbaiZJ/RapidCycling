package com.rapidcycling.controller.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rapidcycling.model.Bike;
import com.rapidcycling.model.dto.BikeRequestDTO;
import com.rapidcycling.service.BikeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class BikeController {

    private final BikeService bikeService;

    @GetMapping(value = "/bikes", produces = "application/json")
    public ResponseEntity<List<Bike>> getBikes() {
        List<Bike> bikes = bikeService.getBikes();

        if (bikes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(bikes, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/bike", produces = "application/json")
    public ResponseEntity<Bike> getBikeByIdRequest(@RequestParam Integer bikeId) {
        Optional<Bike> bike = bikeService.getBikeById(bikeId);

        if (bike.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(bike.get());
        }
    }

    @PostMapping(value = "/bike", consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity<Bike> createBike(@Valid @RequestBody BikeRequestDTO bikeRequest) {
        Bike bike = toEntity(bikeRequest);
        Bike savedBike = bikeService.save(bike);
        return new ResponseEntity<>(savedBike, HttpStatus.CREATED);
    }

    @PutMapping(value = "/bike", consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity<Bike> editBike(@RequestParam Integer bikeId,
            @Valid @RequestBody BikeRequestDTO bikeRequest) {
        Bike bike = toEntity(bikeRequest);
        Optional<Bike> updatedBike = bikeService.updateBikeById(bikeId, bike);

        if (updatedBike.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(updatedBike.get(), HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping(value = "/bike")
    public ResponseEntity<Void> deleteBike(@RequestParam Integer bikeId) {
        boolean deleted = bikeService.deleteBikeById(bikeId);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    private Bike toEntity(BikeRequestDTO request) {
        Bike bike = new Bike();
        bike.setStatus(request.getStatus());
        bike.setMaintenanceFlag(request.getMaintenanceFlag());
        return bike;
    }

}

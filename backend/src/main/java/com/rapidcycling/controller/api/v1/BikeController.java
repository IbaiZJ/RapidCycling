package com.rapidcycling.controller.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rapidcycling.model.Bike;
import com.rapidcycling.service.BikeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BikeController {

    private BikeService bikeService;

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
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike) {

        bikeService.save(bike);
        return new ResponseEntity<>(bike, HttpStatus.CREATED);
    }

}

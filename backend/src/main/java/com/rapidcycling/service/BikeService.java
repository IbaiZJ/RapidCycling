package com.rapidcycling.service;

import java.util.List;
import java.util.Optional;

import com.rapidcycling.model.Bike;

public interface BikeService {
    List<Bike> getBikes();
    Optional<Bike> getBikeById(Integer bikeId);
    Bike save(Bike bike);
    Optional<Bike> findBikeById(Integer bikeId);
}

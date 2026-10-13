package com.rapidcycling.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rapidcycling.model.Bike;
import com.rapidcycling.repository.BikeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BikeServiceImpl implements BikeService {

    private BikeRepository bikeRepository;

    @Override
    public List<Bike> getBikes() {
        return bikeRepository.findAll();
    }

    @Override
    public Optional<Bike> getBikeById(Integer bikeId) {
        return bikeRepository.findById(bikeId);
    }

    @Override
    public Bike save(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public Optional<Bike> findBikeById(Integer bikeId) {
        return bikeRepository.findById(bikeId);
    }
}

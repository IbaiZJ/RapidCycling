package com.rapidcycling.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rapidcycling.model.Bike;
import com.rapidcycling.repository.BikeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BikeServiceImpl implements BikeService {
    
    private static final Logger log = LoggerFactory.getLogger(BikeServiceImpl.class);
    private final BikeRepository bikeRepository;

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

    @Override
    public Optional<Bike> updateBikeById(Integer bikeId, Bike bike) {
        return bikeRepository.findById(bikeId).map(existingBike -> {
            bike.setBikeId(bikeId);
            return bikeRepository.save(bike);
        });
    }

    @Override
    public boolean deleteBikeById(Integer bikeId) {
        if (bikeRepository.existsById(bikeId)) {
            bikeRepository.deleteById(bikeId);
            return true;
        } else {
            return false;
        }
    }
}

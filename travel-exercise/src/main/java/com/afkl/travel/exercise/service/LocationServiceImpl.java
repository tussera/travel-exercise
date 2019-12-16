package com.afkl.travel.exercise.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.afkl.travel.exercise.dao.LocationRepository;
import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.domain.LocationType;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findByTypeAndCode(LocationType type, String code) {
        return locationRepository.findByTypeAndCode(type, code);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> findByCode(String code) {
        return locationRepository.findByCode(code);
    }

    @Override
    public List<Location> findByType(LocationType type) {
        return locationRepository.findByType(type);
    }

}

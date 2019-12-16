package com.afkl.travel.exercise.service;

import java.util.List;
import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.domain.LocationType;

public interface LocationService {

    List<Location> findAll();

    List<Location> findByCode(String code);

    List<Location> findByType(LocationType type);

    List<Location> findByTypeAndCode(LocationType type, String code);

}

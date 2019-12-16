package com.afkl.travel.exercise.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.domain.LocationType;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByTypeAndCode(LocationType type, String code);

    List<Location> findByCode(String code);

    List<Location> findByType(LocationType type);

}

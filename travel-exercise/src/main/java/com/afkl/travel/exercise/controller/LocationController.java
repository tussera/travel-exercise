package com.afkl.travel.exercise.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.domain.LocationResponseDTO;
import com.afkl.travel.exercise.domain.LocationType;
import com.afkl.travel.exercise.domain.Translation;
import com.afkl.travel.exercise.exception.LocationNotFoundException;
import com.afkl.travel.exercise.service.LocationService;
import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/travel")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    @Timed
    public Collection<LocationResponseDTO> getLocations(@RequestHeader(name = "Accept-Language",
            required = false, defaultValue = "EN") Locale locale) {

        final Collection<Location> locations = new ArrayList<>();
        locationService.findAll().forEach(locations::add);

        final Collection<LocationResponseDTO> responseLocations = setupResponse(locations, locale.getLanguage().toUpperCase());

        return responseLocations;
    }

    @GetMapping("/locations/{type}/{code}")
    @Timed
    public Collection<LocationResponseDTO> findLocationsByTypeAndCode(@RequestHeader(name = "Accept-Language",
            required = false, defaultValue = "EN") Locale locale,
            @PathVariable LocationType type,
            @PathVariable String code) {

        final Collection<Location> locations = new ArrayList<>();
        locationService.findByTypeAndCode(type, code).forEach(locations::add);
        if (locations.size() == 0) {
            throw new LocationNotFoundException("Location with Type: " + type + " and Code: " + code
                    + " not found");
        }
        final Collection<LocationResponseDTO> responseLocations = setupResponse(locations, locale.getLanguage().toUpperCase());

        return responseLocations;
    }

    private Collection<LocationResponseDTO> setupResponse(Collection<Location> locations,
            String language) {
        final Collection<LocationResponseDTO> list = new ArrayList<LocationResponseDTO>();
        for (final Location location : locations) {
            final LocationResponseDTO dto = new LocationResponseDTO();
            dto.setCode(location.getCode());
            for (final Translation translation : location.getTranslations()) {
                if (translation.getLanguage().equals(language)) {
                    dto.setName(translation.getName());
                    dto.setDescription(translation.getDescription());
                }
            }
            dto.setType(location.getType());
            dto.setLatitude(location.getLatitute());
            dto.setLongitude(location.getLongitude());
            dto.setParentCode(location.getParent() != null ? location.getParent().getCode() : null);
            dto.setParentType(location.getParent() != null ? location.getParent().getType() : null);

            list.add(dto);
        }
        return list;
    }

}

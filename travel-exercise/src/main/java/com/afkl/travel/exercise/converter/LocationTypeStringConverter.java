package com.afkl.travel.exercise.converter;

import org.springframework.core.convert.converter.Converter;
import com.afkl.travel.exercise.domain.LocationType;

public class LocationTypeStringConverter implements Converter<String, LocationType> {
    @Override
    public LocationType convert(String source) {
        return LocationType.valueOf(source.toLowerCase());
    }

}

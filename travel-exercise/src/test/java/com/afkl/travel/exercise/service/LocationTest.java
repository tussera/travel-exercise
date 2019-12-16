package com.afkl.travel.exercise.service;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.afkl.travel.exercise.Application;
import com.afkl.travel.exercise.domain.Location;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class LocationTest {

    @Autowired
    private LocationService locationService;

    @Test
    public void testLocation() {
        final List<Location> locations = locationService.findAll();
        assertEquals(true, locations.size() > 0);
    }

}

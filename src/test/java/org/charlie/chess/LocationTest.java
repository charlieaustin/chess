package org.charlie.chess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LocationTest {


    @Test
    public void testLocationsBetweenTwoAdjacentPoints() {
        Location location = new Location(0, 1);
        Location sut = new Location(0, 0);

        Set<Location> locations = sut.locationsBetween(location);

        assertEquals(0,locations.size());
    }

    @Test
    public void testLocationsBetweenTwoPointsInALineOnXAxis() {
        Location location = new Location(0, 2);
        Location sut = new Location(0, 0);

        Set<Location> locations = sut.locationsBetween(location);

        assertEquals(1,locations.size());
        assertTrue(locations.contains(new Location(0, 1)));
    }

    @Test
    public void testLocationsBetweenTwoPointsInALineOnYAxis() {
        Location location = new Location(2, 0);
        Location sut = new Location(0, 0);

        Set<Location> locations = sut.locationsBetween(location);

        assertEquals(1, locations.size());
        assertTrue(locations.contains(new Location(1, 0)));
    }

    @Test
    public void testLocationsBetweenTwoPointsDiagonal() {
        Location location = new Location(4, 4);
        Location sut = new Location(0, 0);

        Set<Location> locations = sut.locationsBetween(location);

        assertEquals(3, locations.size());
        assertTrue(locations.contains(new Location(1, 1)));
        assertTrue(locations.contains(new Location(2, 2)));
        assertTrue(locations.contains(new Location(3, 3)));
    }

    @Test
    public void testLocationsBetweenTwoPointsDiagonalOther() {
        Location location = new Location(5, 7);
        Location sut = new Location(3, 5);

        Set<Location> locations = sut.locationsBetween(location);

        assertEquals(1, locations.size());
        assertTrue(locations.contains(new Location(4, 6)));
    }


}
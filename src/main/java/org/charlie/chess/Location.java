package org.charlie.chess;

import java.util.HashSet;
import java.util.Set;

public class Location {

    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Set<Location> locationsBetween(Location dest) {
        HashSet<Location> locations = new HashSet<Location>();
        if (dest.x == this.x && dest.y != this.y) {
            int distance = Math.abs(dest.y - this.y);
            int least = Math.min(dest.y, this.y);
            for (int i = 1; i < distance; i++) {
                locations.add(new Location(x, least + i));
            }
            return locations;
        }

        if (dest.y == this.y && dest.x != this.x) {
            int distance = Math.abs(dest.x - this.x);
            int least = Math.min(dest.x, this.x);
            for (int i = 1; i < distance; i++) {
                locations.add(new Location(least + i, y));
            }
            return locations;
        }

        if (dest.x != this.x && (7 + x - y) == (7 + dest.x - dest.y)) {
            int distance = Math.abs(dest.x - x);
            int leastX = Math.min(dest.x, x);
            int leastY = Math.min(dest.y, y);
            for (int i = 1; i < distance; i++) {
                locations.add(new Location(leastX + i, leastY + i));
            }
            return locations;
        }
        return locations;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        if (y != location.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}

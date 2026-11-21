package com.rapidcycling.model.dto;

/**
 * What the dashboard needs to know about a station.
 * The template reads these values; the rules about "empty" or "full" live here, not in the HTML.
 */
public record StationSummaryDTO(String name, int capacity, int bikes) {

    public enum Availability {
        EMPTY,
        LOW,
        OK,
        ALMOST_FULL,
        FULL
    }

    public int freeDocks() {
        return capacity - bikes;
    }

    public Availability availability() {
        if (bikes == 0) {
            return Availability.EMPTY;
        }
        if (bikes >= capacity) {
            return Availability.FULL;
        }
        if (bikes * 4 < capacity) { // less than 25% of the docks have a bike
            return Availability.LOW;
        }
        if (freeDocks() * 5 < capacity) { // less than 20% of the docks are free
            return Availability.ALMOST_FULL;
        }
        return Availability.OK;
    }

    /** A station where nobody can take a bike, or nobody can return one. */
    public boolean needsRebalancing() {
        Availability availability = availability();
        return availability == Availability.EMPTY || availability == Availability.FULL;
    }
}

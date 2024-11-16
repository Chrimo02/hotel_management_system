package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;

import java.time.LocalDate;

public class RoomService {
    public boolean isAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (LocalDate date = checkIn; date.isBefore(checkOut); date = date.plusDays(1)) {
            if (!room.getAvailabilityMap().getOrDefault(date, false)) {
                return false; // Room is not available for at least one day in the range
            }
        }
        return true;
    }

    public void updateAvailabilityMap(Room room, LocalDate checkIn, LocalDate checkOut, boolean falseMeansBookTrueMeansCancel) {
        for (LocalDate date = checkIn; date.isBefore(checkOut); date = date.plusDays(1)) {
            room.getAvailabilityMap().put(date, true); // Mark dates as available again
        }
    }
}

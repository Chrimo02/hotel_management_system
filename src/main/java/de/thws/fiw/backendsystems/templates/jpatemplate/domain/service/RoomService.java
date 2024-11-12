package de.thws.fiw.backendsystems.templates.jpatemplate.domain.service;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.Hotel;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.Room;

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
    public void bookRoom(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (LocalDate date = checkIn; date.isBefore(checkOut); date = date.plusDays(1)) {
            room.getAvailabilityMap().put(date, false); // Mark dates as booked
        }
    }

    public void cancelRoom(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (LocalDate date = checkIn; date.isBefore(checkOut); date = date.plusDays(1)) {
            room.getAvailabilityMap().put(date, true); // Mark dates as available again
        }
    }
}

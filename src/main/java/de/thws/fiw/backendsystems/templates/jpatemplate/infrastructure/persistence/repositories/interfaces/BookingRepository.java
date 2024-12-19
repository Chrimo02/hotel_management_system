package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {
    Booking createBooking(Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate, List<Room> rooms, List<Guest> guests);
    void updateBooking(Booking booking);
    Booking getBookingById(long bookingId);
}

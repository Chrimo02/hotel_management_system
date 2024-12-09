package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {
    void createBooking(long hotelId, LocalDate checkInDate, LocalDate checkOutDate, List<long> roomIds, List<long> guestIds);
    void updateBooking(Booking booking);
    Booking getBookingById(long bookingId);
}

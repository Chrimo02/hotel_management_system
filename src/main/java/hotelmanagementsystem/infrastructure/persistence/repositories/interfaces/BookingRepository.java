package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {
    Booking createBooking(Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate, List<Room> rooms, List<Guest> guests);
    void updateBooking(Booking booking);
    Booking getBookingById(long bookingId);
    List<Booking> findBookingsByCheckInDate(LocalDate checkInDate);
}

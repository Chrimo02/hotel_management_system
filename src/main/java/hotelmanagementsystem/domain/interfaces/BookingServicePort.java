package hotelmanagementsystem.domain.interfaces;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingServicePort {
    Booking makeBooking(long hotelId, LocalDate checkInDate, LocalDate checkOutDate, List<Class<? extends Room>> roomTypes, List<Long> guestIds) throws HotelNotFoundException;
    void cancelBooking(long bookingID) throws BookingNotFoundException, RoomNotFoundException;
    void guestCheckIn(long bookingId) throws BookingNotFoundException;
    void guestCheckOut(long bookingId) throws BookingNotFoundException;
    Booking getBookingById(long bookingID) throws BookingNotFoundException;
    List<Booking> findAllByCheckInDate(LocalDate checkInDate);
}
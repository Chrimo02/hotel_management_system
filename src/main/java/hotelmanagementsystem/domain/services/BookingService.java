package hotelmanagementsystem.domain.services;
import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.BookingRepository;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.time.LocalDate;

@ApplicationScoped
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final HotelService hotelService;
    private final GuestService guestService;

    @Inject
    public BookingService (BookingRepository bookingRepository, RoomService roomService, HotelService hotelService, GuestService guestService){
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.guestService = guestService;
    }

    private Booking getNotNullBooking(long bookingId) throws BookingNotFoundException {
        Booking booking = bookingRepository.getBookingById(bookingId);
        if (booking == null) throw new BookingNotFoundException("There is no Booking with the specified ID!");
        return booking;
    }

    public void makeBooking(long hotelId, LocalDate checkInDate, LocalDate checkOutDate, List<Class<? extends Room>> roomTypes, List<Long> guestIds){
        List<Room> rooms = roomService.findAvailableRooms(hotelId, roomTypes, checkInDate, checkOutDate);
        List<Guest> guests = guestService.loadGuests(guestIds);
        Hotel hotel = hotelService.getHotelByHotelId(hotelId);
        Booking booking = bookingRepository.createBooking(hotel, checkInDate, checkOutDate, rooms, guests);
        roomService.bookRooms(booking);
    }

    public void cancelBooking(long bookingID) throws BookingNotFoundException, RoomNotFoundException {
        Booking booking = getNotNullBooking(bookingID);
        if(ChronoUnit.DAYS.between(LocalDate.now(), booking.getCheckInDate()) < 2) throw new RuntimeException("Sorry, the cancellation deadline has already expired!");
        for(Room room : booking.getRooms()){
            roomService.cancelRoom(room.getId(), booking.getCheckInDate(),booking.getCheckOutDate());
        }
        booking.setStatus(false);
        bookingRepository.updateBooking(booking);
    }

    public void guestCheckIn(long bookingId) throws BookingNotFoundException {
        Booking booking = getNotNullBooking(bookingId);
        booking.setCheckInTime(LocalDateTime.now());
        bookingRepository.updateBooking(booking);
    }

    public void guestCheckOut(long bookingId) throws BookingNotFoundException {
        Booking booking = getNotNullBooking(bookingId);
        booking.setCheckOutTime(LocalDateTime.now());
        bookingRepository.updateBooking(booking);
    }

    private Booking getBookingById(long bookingID){
        return bookingRepository.getBookingById(bookingID);
    }


}
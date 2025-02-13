package hotelmanagementsystem.domain.services;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.BookingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final HotelService hotelService;
    private final GuestService guestService;

    @Inject
    public BookingService(BookingRepository bookingRepository, RoomService roomService, HotelService hotelService, GuestService guestService){
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

    @Transactional
    public Booking makeBooking(long hotelId, LocalDate checkInDate, LocalDate checkOutDate, List<Class<? extends Room>> roomTypes, List<Long> guestIds) throws HotelNotFoundException {
        // Find available rooms
        List<Room> rooms = roomService.findAvailableRooms(hotelId, roomTypes, checkInDate, checkOutDate);

        // Load guests and hotel
        List<Guest> guests = guestService.loadGuests(guestIds);
        Hotel hotel = hotelService.getHotelByHotelId(hotelId);

        // Create the booking
        Booking booking = bookingRepository.createBooking(hotel, checkInDate, checkOutDate, rooms, guests);

        // Book the rooms (atomic and consistent)
        roomService.bookRooms(booking);

        return booking;
    }

    public void cancelBooking(long bookingID) throws BookingNotFoundException, RoomNotFoundException {
        Booking booking = getNotNullBooking(bookingID);
        if(java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), booking.getCheckInDate()) < 2)
            throw new RuntimeException("Sorry, the cancellation deadline has already expired!");
        for(Room room : booking.getRooms()){
            roomService.cancelRoom(room.getId(), booking);
        }
        booking.setRooms(new ArrayList<>());
        booking.setStatus(false);
        bookingRepository.updateBooking(booking);
    }

    public void guestCheckIn(long bookingId) throws BookingNotFoundException {
        Booking booking = getNotNullBooking(bookingId);
        booking.setCheckInTime(java.time.LocalDateTime.now());
        bookingRepository.updateBooking(booking);
    }

    public void guestCheckOut(long bookingId) throws BookingNotFoundException {
        Booking booking = getNotNullBooking(bookingId);
        booking.setCheckOutTime(java.time.LocalDateTime.now());
        bookingRepository.updateBooking(booking);
    }

    public Booking getBookingById(long bookingID) throws BookingNotFoundException {
        return getNotNullBooking(bookingID);
    }

    public List<Booking> findAllByCheckInDate(LocalDate checkInDate) {
        return bookingRepository.findBookingsByCheckInDate(checkInDate);
    }
}

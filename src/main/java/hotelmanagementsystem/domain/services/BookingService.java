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
import java.time.LocalDateTime;
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
        if (!checkInDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("CheckInDate must be after LocalDate.now");
        }
        if (!checkInDate.isBefore(checkOutDate)){
            throw new IllegalArgumentException("CheckInDate must be before CheckoutDate");
        }
        List<Room> rooms = roomService.findAvailableRooms(hotelId, roomTypes, checkInDate, checkOutDate);

        List<Guest> guests = guestService.loadGuests(guestIds);
        Hotel hotel = hotelService.getHotelByHotelId(hotelId);

        Booking booking = bookingRepository.createBooking(hotel, checkInDate, checkOutDate, rooms, guests);

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
        LocalDate today = LocalDate.now();

        if (today.isBefore(booking.getCheckInDate().minusDays(1))) {
            throw new IllegalStateException("Guest cannot check in more than one day before the booking's check-in date.");
        }
        if (today.isAfter(booking.getCheckOutDate())) {
            throw new IllegalStateException("Guest cannot check in after the booking's check-out date.");
        }

        booking.setCheckInTime(LocalDateTime.now());
        bookingRepository.updateBooking(booking);
    }

    public void guestCheckOut(long bookingId) throws BookingNotFoundException {
        Booking booking = getNotNullBooking(bookingId);
        LocalDate today = LocalDate.now();

        if (booking.getCheckInTime() == null) {
            throw new IllegalStateException("Guest cannot check out without checking in first.");
        }
        if (today.isBefore(booking.getCheckInDate())) {
            throw new IllegalStateException("Guest cannot check out before their check-in date.");
        }
        if (today.isAfter(booking.getCheckOutDate())) {
            throw new IllegalStateException("Guest cannot check out after their check-out date.");
        }

        booking.setCheckOutTime(LocalDateTime.now());
        bookingRepository.updateBooking(booking);
    }


    public Booking getBookingById(long bookingID) throws BookingNotFoundException {
        return getNotNullBooking(bookingID);
    }

    public List<Booking> findAllByCheckInDate(LocalDate checkInDate) {
        return bookingRepository.findBookingsByCheckInDate(checkInDate);
    }
}

package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.BookingNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.GuestNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.BookingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        bookingRepository.createBooking(hotel, checkInDate, checkOutDate, rooms, guests);
    }

    public void cancelBooking(long bookingID) throws BookingNotFoundException{
        Booking booking = getNotNullBooking(bookingID);
        if(ChronoUnit.DAYS.between(LocalDate.now(), booking.getCheckInDate()) < 2) throw new RuntimeException("Sorry, the cancellation deadline has already expired!");
        for(Room room : booking.getRooms()){
            roomService.cancelRoom(room.getId(), booking.getCheckInDate(),booking.getCheckOutDate());
        }
        booking.setStatus(false);
        bookingRepository.updateBooking(booking);
    }

    void guestCheckIn(long bookingId) throws BookingNotFoundException {
        Booking booking = getNotNullBooking(bookingId);
        booking.setCheckInTime(LocalDateTime.now());
        bookingRepository.updateBooking(booking);
    }

    void guestCheckOut(long bookingId) throws BookingNotFoundException {
        Booking booking = getNotNullBooking(bookingId);
        booking.setCheckOutTime(LocalDateTime.now());
        bookingRepository.updateBooking(booking);
    }

    private Booking getBookingById(long bookingID){
        return bookingRepository.getBookingById(bookingID);
    }
}
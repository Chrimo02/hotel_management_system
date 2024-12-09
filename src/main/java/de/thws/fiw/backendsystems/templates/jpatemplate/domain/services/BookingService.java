package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.BookingNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.GuestNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.BookingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@ApplicationScoped
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final HotelService hotelService;
    @Inject
    public BookingService (BookingRepository bookingRepository, RoomService roomService, HotelService hotelService){
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    private Booking getNotNullBooking(long bookingId) throws BookingNotFoundException {
        Booking booking = bookingRepository.getBookingById(bookingId);
        if (booking == null) throw new BookingNotFoundException("There is no Booking with the specified ID!");
        else return booking;
    }

    public void makeBooking(long hotelId, LocalDate checkInDate, LocalDate checkOutDate, List<Class<? extends Room>> roomTypes, List<long> guests){
        List<long> roomIds = new ArrayList<long>();
        Room foundRoom;
        for(Class<? extends Room> roomType : roomTypes){
            foundRoom = findAvailableRoom(hotelId, roomType, checkInDate, checkOutDate);
            if(foundRoom == null) throw new RuntimeException("Available Room not found");
            roomIds.add(foundRoom.getId());
        }
        bookingRepository.createBooking(hotelId, checkInDate, checkOutDate, roomIds, guests);
    }

    public void cancelBooking(long bookingID) throws BookingNotFoundException{
        Booking booking = getNotNullBooking(bookingID);
        for(Room room : booking.getRooms()){
            roomService.cancelRoom(room.getId(), booking.getCheckInDate(),booking.getCheckOutDate());
        }
        booking.setStatus(false);
        bookingRepository.updateBooking(booking);
    }

    private Room findAvailableRoom(long hotelID, Class<? extends Room> roomType, LocalDate checkInToCheck, LocalDate checkOutToCheck) {
        Hotel hotel = hotelService.getHotelByHotelId(hotelID);
        for (Room room : hotel.getRooms()) {
            if (roomType.isInstance(room)) {
                if (roomService.isAvailable(room.getId(), checkInToCheck, checkOutToCheck)) {
                    return room;
                }
            }
        }
        return null;
    }

    void guestCheckIn(long bookingID){
        Booking booking = getBookingById(bookingID);
        if (booking != null) {
            booking.setCheckedIn(true);
            booking.setCheckInTime(LocalDateTime.now());
            //update booking repo
        }
    }

    void guestCheckOut(long bookingID){
        Booking booking = getBookingById(bookingID);
        if (booking != null) {
            booking.setCheckedOut(true);
            booking.setCheckOutTime(LocalDateTime.now());
            //update booking repo
        }
    }

    private Booking getBookingById(long bookingID){
        //access repo
        return null;
    }
}
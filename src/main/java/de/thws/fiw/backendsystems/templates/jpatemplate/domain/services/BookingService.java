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
        else return booking;
    }

    public void makeBooking(long hotelId, LocalDate checkInDate, LocalDate checkOutDate, List<Class<? extends Room>> roomTypes, List<Long> guests){
        List<Room> rooms = new ArrayList<>();
        Room foundRoom;
        for(Class<? extends Room> roomType : roomTypes){
            foundRoom = findAvailableRoom(hotelId, roomType, checkInDate, checkOutDate);
            if(foundRoom == null) throw new RuntimeException("Available Room not found");
            rooms.add(foundRoom);
        }
        List<Guest> guestList = new ArrayList<>();
        for(long guestId : guests){
            try{
                 Guest guest = guestService.getGuestById(guestId);
                 guestList.add(guest);
            } catch (GuestNotFoundException e) {
                 throw new RuntimeException("Guest with Id" + guestId + " not found");
            }
      }
        bookingRepository.createBooking(hotelId, checkInDate, checkOutDate, rooms, guestList);
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
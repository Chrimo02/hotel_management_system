package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class BookingService {
    public BookingService(RoomService roomService, HotelService hotelService){
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    RoomService roomService;
    HotelService hotelService;
    public void makeBooking(long hotelId, LocalDate checkInDate, LocalDate checkOutDate, List<Class<? extends Room>> roomTypes, long ... guests){
        List<Room> rooms = new ArrayList<>();
        Room foundRoom;
        for(Class<? extends Room> roomType : roomTypes){
            foundRoom = findAvailableRoom(hotelId, roomType, checkInDate, checkOutDate);
            if(foundRoom == null) throw new RuntimeException("Available Room not found");
            rooms.add(foundRoom);
        }
        //Access Repo
    }

    public void cancelBooking(long bookingID){
        Booking booking = getBookingById(bookingID);
        for(Room room : booking.getRooms()){
            roomService.cancelRoom(room.getId(), booking.getCheckInDate(),booking.getCheckOutDate());
        }
        booking.setStatus(false);
        //update booking repo
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
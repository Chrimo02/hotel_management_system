package de.thws.fiw.backendsystems.templates.jpatemplate.domain.service;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.*;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

public class BookingService {

    RoomService roomService = new RoomService();
    public void makeBooking(Hotel hotel, LocalDate desiredCheckIn,LocalDate desiredCheckout, Guest ... guests){
        Room chosenRoom;
        if (guests.length == 1) chosenRoom = findAvailableRoom(hotel,SingleRoom.class,desiredCheckIn,desiredCheckout);
        else chosenRoom = findAvailableRoom(hotel,DoubleRoom.class,desiredCheckIn,desiredCheckout);
        //BookingEntity newBooking = new BookingEntity(desiredCheckIn,desiredCheckout, chosenRoom, guests);


    }

    public void cancelBooking(Booking b){
        //TODO: Genauere Bedingungen für Stornierung noch implementieren
        roomService.cancelRoom(b.getRoom(), b.getCheckInDate(), b.getCheckOutDate());
        b.setStatus(false);
    }
    public boolean isActive(Booking b){
        return b.getStatus();
    }


    public Room findAvailableRoom(Hotel hotel, Class<? extends Room> roomType, LocalDate checkInToCheck, LocalDate checkOutToCheck) {
        for (Room room : hotel.getRooms()) {
            // Check if the room is an instance of the specified roomType (SingleRoom or DoubleRoom)
            if (roomType.isInstance(room)) {
                // Check if the room is available for the specified dates
                if (roomService.isAvailable(room, checkInToCheck, checkOutToCheck)) {
                    return room; // Return the first available room of the specified type
                }
            }
        }
        return null; // No available room of the specified type found
    }



    public void rateBooking(Booking booking, String comment, int starRating) throws RuntimeException{
        if (starRating < 1 || starRating > 5) throw new IllegalArgumentException("Invalid rating");
        HotelRating rating = HotelRating.values()[starRating - 1];
        if(comment != null) rating.setCommentRating(comment);
        booking.setRating(rating);
    }
}

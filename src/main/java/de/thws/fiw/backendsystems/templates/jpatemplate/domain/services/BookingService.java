package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;

import java.time.LocalDate;

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



    //    public boolean isOverlapping(Booking booking, LocalDate otherCheckInDate, LocalDate otherCheckOutDate) {
//        // Two date ranges overlap if one starts before the other ends and vice versa
//        return (booking.getCheckInDate().isBefore(otherCheckOutDate) && otherCheckInDate.isBefore(booking.getCheckOutDate()));
//    }
//
//    public Room returnFreeRoom(Hotel hotel, Class<? extends Room> kindOfRoom, LocalDate otherCheckInDate, LocalDate otherCheckOutDate){
//        for (Room room : hotel.getRooms()){
//
//        }
//    }
//
//    /**
//     * Überprüft, ob das Zimmer für die angegebenen Daten verfügbar ist.
//     */
//    public boolean isAvailableForDates(Hotel hotel, long roomID, LocalDate checkInDate, LocalDate checkOutDate) {
//        for (Booking booking : hotel.getBookings()) {
//            if (BookingService.isOverlapping(checkInDate, checkOutDate)) {
//                return false;  // Wenn es eine Überlappung gibt, ist das Zimmer nicht verfügbar
//            }
//        }
//        return true;
//    }
//



}

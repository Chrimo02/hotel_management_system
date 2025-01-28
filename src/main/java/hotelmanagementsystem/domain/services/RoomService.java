package hotelmanagementsystem.domain.services;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomIdentifierRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomRepository;
import hotelmanagementsystem.domain.models.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelService hotelService;
    private final RoomIdentifierRepository roomIdentifierRepository;

    @Inject
    public RoomService(RoomRepository roomRepository, HotelService hotelService, RoomIdentifierRepository roomIdentifierRepository) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
        this.roomIdentifierRepository = roomIdentifierRepository;
    }

    public Room getRoomById(long roomId) throws RoomNotFoundException {
        Room room = roomRepository.findRoomById(roomId);
        if (room == null) throw new RoomNotFoundException("There is no Booking with the specified ID!");
        return room;
    }
    public void bookRooms(Booking booking) {
        List<Room> rooms = booking.getRooms();
        for(Room room : rooms){
            room.getBookings().add(booking);
            roomRepository.updateRoom(room);
        }
    }

    public void cancelRoom(long roomId, LocalDate checkIn, LocalDate checkOut) throws RoomNotFoundException, BookingNotFoundException {
        Room room = getRoomById(roomId);
        Booking bookingToCancel = findBookingByDates(room, checkIn, checkOut);

        if (bookingToCancel != null) {
            room.getBookings().remove(bookingToCancel);
            roomRepository.updateRoom(room);
        } else {
            throw new BookingNotFoundException("Booking not found for the specified dates.");
        }
    }

    // Helper method to check if a room is available for the given booking
    private boolean isAvailable(Room room, Booking booking) {
        Set<Booking> bookings = room.getBookings();
        for (Booking existingBooking : bookings) {
            if (isOverlapping(existingBooking, booking)) {
                return false; // Found an overlapping booking
            }
        }
        return true;
    }

    // Method to check if two bookings overlap
    private boolean isOverlapping(Booking existingBooking, Booking newBooking) {
        return !(newBooking.getCheckOutDate().isBefore(existingBooking.getCheckInDate()) ||
                newBooking.getCheckInDate().isAfter(existingBooking.getCheckOutDate()));
    }

    // Helper method to find a booking by check-in and check-out dates
    private Booking findBookingByDates(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking booking : room.getBookings()) {
            if (booking.getCheckInDate().equals(checkIn) && booking.getCheckOutDate().equals(checkOut)) {
                return booking;
            }
        }
        return null; // No matching booking found
    }

    public long createRoom(double pricePerNight, RoomIdentifier roomIdentifier, long hotelId, Class<? extends Room> roomType) {
        Hotel hotel = hotelService.getHotelByHotelId(hotelId);
        Room room;
        if (roomType.equals(SingleRoom.class)) {
            room = new SingleRoom.Builder(pricePerNight, roomIdentifier, hotel).build();
        }
        else if (roomType.equals(DoubleRoom.class)) {
            room = new DoubleRoom.Builder(pricePerNight, roomIdentifier, hotel).build();
        }
        else throw new RuntimeException("Invalid room type!"); //wird nicht gebraucht, wenn wir sicher sind, dass nur 2 mögliche Room Arten bekommen werden
        long id = roomRepository.saveRoom(room);
        roomIdentifierRepository.saveRoomIdentifier(roomIdentifier);
        return id;
    }

    public void removeRoom(long roomId) {
        roomRepository.removeRoom(roomId);
    }

    public List<Room> findAvailableRooms(long hotelId, List<Class<? extends Room>> roomTypes, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> rooms = new ArrayList<>();
        for (Class<? extends Room> roomType : roomTypes) {
            Room room = findAvailableRoom(hotelId, roomType, checkInDate, checkOutDate);
            if (room == null) {
                throw new RuntimeException("Available Room not found");
            }
            rooms.add(room);
        }
        return rooms;
    }

    public boolean isAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        Set<Booking> bookings = room.getBookings();
        for (Booking existingBooking : bookings) {
            if (isOverlapping(existingBooking, checkIn, checkOut)) {
                return false; // Found an overlapping booking
            }
        }
        return true;
    }

    private boolean isOverlapping(Booking existingBooking, LocalDate checkIn, LocalDate checkOut) {
        return !(checkOut.isBefore(existingBooking.getCheckInDate()) ||
                checkIn.isAfter(existingBooking.getCheckOutDate()));
    }

    private Room findAvailableRoom(long hotelID, Class<? extends Room> roomType, LocalDate checkInToCheck, LocalDate checkOutToCheck) {
        Hotel hotel = hotelService.getHotelByHotelId(hotelID);
        for (Room room : hotel.getRooms()) {
            if (roomType.isInstance(room)) {
                if (isAvailable(room, checkInToCheck, checkOutToCheck)) {
                    return room;
                }
            }
        }
        return null;
    }

}
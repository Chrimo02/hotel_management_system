package hotelmanagementsystem.domain.interfaces;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;

import java.time.LocalDate;
import java.util.List;

public interface RoomServicePort {
    Room getRoomById(long roomId) throws RoomNotFoundException;
    void bookRooms(Booking booking);
    void cancelRoom(long roomId, Booking bookingToCancel) throws RoomNotFoundException, BookingNotFoundException;
    boolean isAvailable(Room room, LocalDate checkIn, LocalDate checkOut);
    Room createRoom(double pricePerNight, RoomIdentifier roomIdentifier, long hotelId, Class<? extends Room> roomType) throws HotelNotFoundException;
    void removeRoom(long roomId) throws RoomNotFoundException;
    Room updatePricePerNight(long roomId, Double newPricePerNight) throws RoomNotFoundException;
    Room updateRoomIdentifier(long roomId, RoomIdentifier newRoomIdentifier) throws RoomNotFoundException;
    List<Room> findAvailableRooms(long hotelId, List<Class<? extends Room>> roomTypes, LocalDate checkInDate, LocalDate checkOutDate) throws HotelNotFoundException;

    }
package hotelmanagementsystem.domain.services;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomIdentifierRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
        if (room == null) throw new RoomNotFoundException("There is no Room with the specified ID!");
        return room;
    }

    @Transactional
    public void bookRooms(Booking booking) {
        for (Room room : booking.getRooms()) {
            // Check if the room is available for the booking dates
            if (!isAvailable(room, booking.getCheckInDate(), booking.getCheckOutDate())) {
                throw new RuntimeException("Room with ID " + room.getId() + " is no longer available for the specified dates.");
            }
            // Add the booking to the room's bookings
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

    public boolean isAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking existingBooking : room.getBookings()) {
            if (isOverlapping(existingBooking, checkIn, checkOut)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOverlapping(Booking existingBooking, LocalDate newCheckIn, LocalDate newCheckOut) {
        return newCheckIn.isBefore(existingBooking.getCheckOutDate()) &&
                newCheckOut.isAfter(existingBooking.getCheckInDate());

    }

    private Booking findBookingByDates(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking booking : room.getBookings()) {
            if (booking.getCheckInDate().equals(checkIn) && booking.getCheckOutDate().equals(checkOut)) {
                return booking;
            }
        }
        return null;
    }

    public Room createRoom(double pricePerNight, RoomIdentifier roomIdentifier, long hotelId, Class<? extends Room> roomType) throws HotelNotFoundException {
        Hotel hotel = hotelService.getHotelByHotelId(hotelId);
        Room room;
        if (roomType.equals(SingleRoom.class)) {
            room = new SingleRoom.Builder(pricePerNight, roomIdentifier, hotel).build();
        } else if (roomType.equals(DoubleRoom.class)) {
            room = new DoubleRoom.Builder(pricePerNight, roomIdentifier, hotel).build();
        } else {
            throw new RuntimeException("Invalid room type!");
        }
        Room newRoom = roomRepository.saveRoom(room);
        roomIdentifierRepository.saveRoomIdentifier(roomIdentifier);
        return newRoom;
    }

    public void removeRoom(long roomId) throws RoomNotFoundException {
        Room room = getRoomById(roomId);
        // Prüfe, ob aktive Buchungen (Status true) existieren
        boolean hasActiveBookings = room.getBookings().stream().anyMatch(b -> b.getStatus());
        if (hasActiveBookings) {
            throw new RuntimeException("Cannot remove room with active bookings.");
        }
        roomRepository.removeRoom(roomId);
    }

    public Room updatePricePerNight(long roomId, Double newPricePerNight) throws RoomNotFoundException {
        Room room = roomRepository.findRoomById(roomId);
        if (room == null) {
            throw new RoomNotFoundException("Room with ID " + roomId + " not found.");
        }
        if (newPricePerNight != null) {
            room.setPricePerNight(newPricePerNight);
        }
        roomRepository.updateRoom(room);
        return room;
    }

    public Room updateRoomIdentifier(long roomId, RoomIdentifier newRoomIdentifier) throws RoomNotFoundException {
        Room room = roomRepository.findRoomById(roomId);
        if (room == null) {
            throw new RoomNotFoundException("Room with ID " + roomId + " not found.");
        }
        if (newRoomIdentifier != null) {
            room.setRoomIdentifier(newRoomIdentifier);
            roomIdentifierRepository.saveRoomIdentifier(newRoomIdentifier);
        }
        roomRepository.updateRoom(room);
        return room;
    }

    public List<Room> findAvailableRooms(long hotelId, List<Class<? extends Room>> roomTypes, LocalDate checkInDate, LocalDate checkOutDate) throws HotelNotFoundException {
        List<Room> availableRooms = new ArrayList<>();
        for (Class<? extends Room> roomType : roomTypes) {
            Room room = findAvailableRoom(hotelId, roomType, checkInDate, checkOutDate);
            if (room == null) {
                throw new RuntimeException("Available Room not found for room type: " + roomType.getSimpleName());
            }
            availableRooms.add(room);
        }
        return availableRooms;
    }

    private Room findAvailableRoom(long hotelId, Class<? extends Room> roomType, LocalDate checkIn, LocalDate checkOut) throws HotelNotFoundException {
        Hotel hotel = hotelService.getHotelByHotelId(hotelId);
        for (Room room : hotel.getRooms()) {
            if (roomType.isInstance(room) && isAvailable(room, checkIn, checkOut)) {
                return room;
            }
        }
        return null;
    }
}

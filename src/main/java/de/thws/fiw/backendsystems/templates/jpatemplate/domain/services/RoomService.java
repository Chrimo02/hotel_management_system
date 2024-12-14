package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelRepository;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.RoomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.Map;
@ApplicationScoped
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelService hotelService;
    @Inject
    public RoomService(RoomRepository roomRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
    }

    private Room getRoomById(long roomId) {
        Room room = roomRepository.findRoomById(roomId);
        if (room == null) throw new IllegalArgumentException("There is no room with id: " + roomId);
        //Optional verwenden?
        return room;
    }
    public void bookRoom(long roomId, LocalDate checkIn, LocalDate checkOut) {
        updateAvailabilityMap(roomId, checkIn, checkOut, false);
    }
    public void cancelRoom(long roomId, LocalDate checkIn, LocalDate checkOut) {
        updateAvailabilityMap(roomId, checkIn, checkOut, true);
    }
    public void createRoom(double pricePerNight, RoomIdentifier roomIdentifier, long hotelId, Class<? extends Room> roomType) {
        Hotel hotel = hotelService.getHotelByHotelId(hotelId);
        Room room;
        if (roomType.equals(SingleRoom.class)) {
            room = new SingleRoom.Builder(pricePerNight, roomIdentifier, hotel).build();
        }
        else if (roomType.equals(DoubleRoom.class)) {
            room = new DoubleRoom.Builder(pricePerNight, roomIdentifier, hotel).build();
        }
        else throw new RuntimeException("Invalid room type!"); //wird nicht gebraucht, wenn wir sicher sind, dass nur 2 mögliche Room Arten bekommen werden
        roomRepository.saveRoom(room);
    }
    public boolean isAvailable(long roomId, LocalDate checkIn, LocalDate checkOut) {
        Room room = getRoomById(roomId);
        Map<LocalDate, Boolean> availabilityMap = room.getAvailabilityMap();
        for (LocalDate i = checkIn; !i.isAfter(checkOut); i = i.plusDays(1)) {
            if (!availabilityMap.getOrDefault(i, false))
                return false;
        }
        return true;
    }
    public double getPriceById(long roomId) {
        Room room = getRoomById(roomId);
        return room.getPricePerNight();
    }
    public void updateAvailabilityMap(long roomId, LocalDate checkIn, LocalDate checkOut, boolean status) {
        Room room = getRoomById(roomId);
        Map<LocalDate, Boolean> availabilityMap = room.getAvailabilityMap();
        for (LocalDate i = checkIn; !i.isAfter(checkOut); i = i.plusDays(1)) {
            availabilityMap.put(i, status);
        }
    }
    public boolean removeRoom(long roomId) {
        roomRepository.removeRoom(roomId);
        return (getRoomById(roomId) == null);
    }
}
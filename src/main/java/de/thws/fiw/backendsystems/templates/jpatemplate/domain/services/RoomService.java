package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;

import java.time.LocalDate;

public class RoomService {
    //eine Instanz von Hotelservice estellen und die getHotelById
    private final RoomPort roomPort; //die ports mit repository ersetzen
    private final HotelPort hotelPort;
    public RoomService (RoomPort roomPort, HotelPort hotelPort) {
        this.hotelPort = hotelPort;
        this.roomPort = roomPort;
    }
    private Room getRoomById(long roomId) {

        Room room = roomPort.findRoomById(roomId);
        if (room == null) //wird nicht gebraucht, wenn wir Optional verwenden, oder?
            throw new IllegalArgumentException("A room with id: " + roomId + " was not found");
        return room;
    }
    private Hotel getHotelById(long hotelId) {
        Hotel hotel = hotelPort.findHotelById(hotelId);
        if (hotel == null) //dieselbe Frage wie oben
            throw new IllegalArgumentException("A hotel with id:" + hotelId + "was not found");
        return hotel;
    }
    public void bookRoom(long roomId, LocalDate checkIn, LocalDate checkOut) {
        updateAvailabiltyMap(roomId, checkIn, checkOut, false);
    }

    public void cancelRoom(long roomId, LocalDate checkIn, LocalDate checkOut) {
        updateAvailabiltyMap(roomId, checkIn, checkOut, true);
    }
    public void createRoom(double pricePerNight, RoomIdentifier roomIdentifier, long hotelId, Class<? extends Room> roomType) {
        Hotel hotel = getHotelById(hotelId);
        Room room;
        if (roomType.equals(SingleRoom.class)) {
            room = new SingleRoom(pricePerNight, roomIdentifier, hotel);
        }
        else if(roomType.equals(DoubleRoom.class)) {
            room = new DoubleRoom(pricePerNight, roomIdentifier, hotel);
        }
        else throw new RuntimeException("Invalid room type!");
        roomPort.saveRoom(room);
    }
    public boolean isAvailable(long roomId, LocalDate checkIn, LocalDate checkOut) {
        Room room = getRoomById(roomId);
        Map<LocalDate, Boolean> availabilyMap = room.getAvailabilityMap();
        for(LocalDate i = checkIn; !i.isAfter(checkOut); i = i.plusDays(1)) {
            if (!availabilyMap.getOrDefault(i, false))
                return false;
        }
        return true;
    }
    public double getPriceById(long roomId) {
        Room room = getRoomById(roomId);
        return room.getPricePerNight();
    }
    public void updateAvailabiltyMap(long roomId, LocalDate checkIn, LocalDate checkOut, boolean status) {
        Room room = getRoomById(roomId);
        Map<LocalDate, Boolean> availabilyMap = room.getAvailabilityMap();
        for(LocalDate i = checkIn; !i.isAfter(checkOut); i = i.plusDays(1)) {
            availabilyMap.put(i, status);
        }
    }
    public boolean removeRoom(long roomId) { //boolean?
        roomPort.removeRoom(roomId);
    }
}

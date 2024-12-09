package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

public class DoubleRoom extends Room{
    public DoubleRoom(long id, double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
        super(id, pricePerNight, roomIdentifier, hotel);
    }
    public DoubleRoom(double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
        super(pricePerNight, roomIdentifier, hotel);
    }
}

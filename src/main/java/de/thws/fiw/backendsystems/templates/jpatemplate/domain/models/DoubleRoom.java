package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

public class DoubleRoom extends Room{
    public DoubleRoom(long id, double pricePerNight, boolean available, RoomIdentifier roomIdentifier, Hotel hotel) {
        super(id, pricePerNight, roomIdentifier, hotel);
    }
}

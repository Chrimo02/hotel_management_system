package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

public class DoubleRoom extends Room{
    public DoubleRoom(long id, double pricePerNight, boolean available, RoomIdentifier roomIdentifier, Hotel hotel) {
        super(id, pricePerNight, available, roomIdentifier, hotel);
    }
}

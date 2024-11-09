package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

public class SingleRoom extends Room{
    public SingleRoom(long id, double pricePerNight, boolean available, RoomIdentifier roomIdentifier, Hotel hotel) {
        super(id, pricePerNight, available, roomIdentifier, hotel);
    }
}

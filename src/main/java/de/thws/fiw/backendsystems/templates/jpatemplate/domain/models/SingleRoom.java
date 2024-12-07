package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

public class SingleRoom extends Room{
    public SingleRoom(long id, double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
        super(id, pricePerNight, roomIdentifier, hotel);
    }
    public SingleRoom(double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
        super(pricePerNight, roomIdentifier, hotel);
    }
    //vllt nur eine Klasse Room machen, statt double- und single- room?
}
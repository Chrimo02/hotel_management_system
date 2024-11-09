package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

public abstract class Room {

    //einzelne Room-types mit Vererbung implementieren
    private long id;
    private double pricePerNight;
    private boolean available;
    private RoomIdentifier roomIdentifier;
    private Hotel hotel;

    public Room(long id, double pricePerNight, boolean available, RoomIdentifier roomIdentifier, Hotel hotel) {
        this.id = id;
        this.pricePerNight = pricePerNight;
        this.available = available;
        this.roomIdentifier = roomIdentifier;
        this.hotel = hotel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public RoomIdentifier getRoomIdentifier() {
        return roomIdentifier;
    }

    public void setRoomIdentifier(RoomIdentifier roomIdentifier) {
        this.roomIdentifier = roomIdentifier;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}

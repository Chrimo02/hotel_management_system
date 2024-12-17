package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

import java.util.Set;
import java.util.TreeSet;

public abstract class Room {
    private long id;
    private double pricePerNight;
    private RoomIdentifier roomIdentifier;
    private Hotel hotel;
    private Set<Booking> bookings = new TreeSet<>(); // TreeSet für sortierte Buchungen

    protected Room(long id, double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
        this.id = id;
        this.pricePerNight = pricePerNight;
        this.roomIdentifier = roomIdentifier;
        this.hotel = hotel;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public long getId() {
        return id;
    }
    public RoomIdentifier getRoomIdentifier() {
        return roomIdentifier;
    }
    public void setRoomIdentifier(RoomIdentifier roomIdentifier) {
        this.roomIdentifier = roomIdentifier;
    }
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
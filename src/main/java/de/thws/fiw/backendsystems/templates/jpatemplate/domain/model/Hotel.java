package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

import java.util.List;

public class Hotel {

    private final long id;
    private String name;
    private String description;
    private HotelLocation location;
    private double averageRating;     //Todo: Im Hotel-service noch Methode die alle bookings durchläuft und dann eine Durchschnittsbewertung speichert implementieren

    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel(long id, String name, String description, HotelLocation location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HotelLocation getLocation() {
        return location;
    }

    public void setLocation(HotelLocation location) {
        this.location = location;
    }

    public double getAverageRating(){
        return this.averageRating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRoom(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public void addRoom(Room room){
        rooms.add(room);
    }
}

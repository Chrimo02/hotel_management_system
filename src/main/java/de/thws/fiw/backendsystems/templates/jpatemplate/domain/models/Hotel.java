package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {

    private final long id;
    private String name;
    private String description;
    private HotelLocation location;
    private double averageRating;     //Todo: Im Hotel-service noch Methode die alle bookings durchläuft und dann eine Durchschnittsbewertung speichert implementieren

    private List<Room> rooms;
    private List<Booking> bookings;
    private final Map<Long, HotelRating> ratings;

    private Hotel(HotelBuilder hotelBuilder) {
        this.id = hotelBuilder.id;
        this.name = hotelBuilder.name;
        this.description = hotelBuilder.description;
        this.location = hotelBuilder.location;
        this.ratings = new HashMap<>();
        this.averageRating = 0.0;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public HotelLocation getLocation() {
        return location;
    }
    public double getAverageRating(){
        return this.averageRating;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public List<Booking> getBookings() {return bookings;}
    public Map<Long, HotelRating> getRatings() {return ratings;}

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
    public void setLocation(HotelLocation location) {
        this.location = location;
    }
    public void setRoom(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addRating(long guestId, HotelRating rating) { this.ratings.put(guestId, rating);}
    public void addRoom(Room room){rooms.add(room);}


    public static class HotelBuilder {
        private final long id;
        private String name;
        private String description;
        private HotelLocation location;
        private List<Room> rooms;

        public HotelBuilder(long id) {
            this.id = id;
        }

        public HotelBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public HotelBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public HotelBuilder withLocation(HotelLocation location) {
            this.location = location;
            return this;
        }

        public Hotel build() {
            return new Hotel(this);
        }

    }
}

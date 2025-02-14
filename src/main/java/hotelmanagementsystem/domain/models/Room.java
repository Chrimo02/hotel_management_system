package hotelmanagementsystem.domain.models;

import java.util.Set;
import java.util.TreeSet;

public abstract class Room {
    private final long id;
    private double pricePerNight;
    private RoomIdentifier roomIdentifier;
    private Hotel hotel;
    private Set<Booking> bookings = new TreeSet<>();

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

    public static class Builder {
        private long id;
        private double pricePerNight;
        private RoomIdentifier roomIdentifier;
        private Hotel hotel;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withPricePerNight(double pricePerNight) {
            this.pricePerNight = pricePerNight;
            return this;
        }

        public Builder withRoomIdentifier(RoomIdentifier roomIdentifier) {
            this.roomIdentifier = roomIdentifier;
            return this;
        }

        public Builder withHotel(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }

        public Room build() {
            return new Room(id, pricePerNight, roomIdentifier, hotel) {
            };
        }
    }
}

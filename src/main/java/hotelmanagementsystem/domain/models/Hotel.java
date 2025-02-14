package hotelmanagementsystem.domain.models;


import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private Long id;
    private String name;
    private String description;
    private HotelLocation location;
    private double averageRating;

    private List<Room> rooms;
    private List<Booking> bookings;
    private final List<HotelRating> ratings;

    private Hotel(HotelBuilder hotelBuilder) {
        this.id = hotelBuilder.id;
        this.name = hotelBuilder.name;
        this.rooms = hotelBuilder.rooms;
        this.description = hotelBuilder.description;
        this.location = hotelBuilder.location;
        this.ratings = hotelBuilder.ratings;
        this.averageRating = hotelBuilder.averageRating;
        this.bookings = hotelBuilder.bookings;
    }

    public Long getId() {
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
    public List<HotelRating> getRatings() {return ratings;}

    public void setName(String name) {
        this.name = name;
    }
    public void setId(Long id) { this.id = id; }
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

    public void addRating(HotelRating rating) { this.ratings.add(rating); }
    public void addRoom(Room room){ rooms.add(room); }

    public static class HotelBuilder {

        private Long id;
        private String name;
        private String description;
        private HotelLocation location;
        private List<Room> rooms;
        private List<Booking> bookings;
        private List<HotelRating> ratings = new ArrayList<>();
        private double averageRating = 0.0;

        public HotelBuilder() {
        }

        public HotelBuilder withId(Long id) {
            this.id = id;
            return this;
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

        public HotelBuilder withRoomsList(List<Room> rooms){
            this.rooms = rooms != null ? rooms : new ArrayList<>();
            return this;
        }

        public HotelBuilder withBookingList(List<Booking> bookings){
            this.bookings = bookings;
            return this;
        }

        public HotelBuilder withRatingMap(List<HotelRating> ratings){
            if (ratings != null){
                this.ratings = ratings;
            }
            return this;
        }
        public HotelBuilder withAverageRating(double avg){
            this.averageRating = avg;
            return this;
        }

        public Hotel build() {
            return new Hotel(this);
        }
    }
}

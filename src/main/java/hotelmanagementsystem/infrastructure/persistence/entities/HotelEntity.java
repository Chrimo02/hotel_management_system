package hotelmanagementsystem.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotels")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private HotelLocationEntity location;

    @Column(name = "average_rating")
    private double averageRating;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RoomEntity> rooms;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookingEntity> bookings;

    // Ratings are mapped unidirectionally.
    // JPA will write the hotel's id into the hotel_id column of hotel_ratings.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private List<HotelRatingEntity> ratings;

    protected HotelEntity() {}

    private HotelEntity(HotelBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.location = builder.location;
        this.averageRating = builder.averageRating;
        this.rooms = builder.rooms;
        this.bookings = builder.bookings;
        this.ratings = builder.ratings;
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

    public HotelLocationEntity getLocation() {
        return location;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public List<HotelRatingEntity> getRatings() {
        return ratings;
    }
    public void setRatings(List<HotelRatingEntity> ratings){
        this.ratings = ratings;
    }

    public static class HotelBuilder {
        private Long id;
        private String name;
        private String description;
        private HotelLocationEntity location;
        private double averageRating = 0.0;
        private List<RoomEntity> rooms;
        private List<BookingEntity> bookings;
        private List<HotelRatingEntity> ratings;

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

        public HotelBuilder withLocation(HotelLocationEntity location) {
            this.location = location;
            return this;
        }

        public HotelBuilder withRooms(List<RoomEntity> rooms) {
            this.rooms = rooms;
            return this;
        }

        public HotelBuilder withBookings(List<BookingEntity> bookings) {
            this.bookings = bookings;
            return this;
        }

        public HotelBuilder withRatings(List<HotelRatingEntity> ratings) {
            this.ratings = ratings;
            return this;
        }
        public HotelBuilder withAverageRating(double avg){
            this.averageRating = avg;
            return this;
        }

        public HotelEntity build() {
            return new HotelEntity(this);
        }
    }
}

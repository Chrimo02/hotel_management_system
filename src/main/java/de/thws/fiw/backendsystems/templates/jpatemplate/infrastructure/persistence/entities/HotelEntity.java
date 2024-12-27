package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Map;

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

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HotelRatingEntity> ratings;

    /*@ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "hotel_ratings", joinColumns = @JoinColumn(name = "hotel_id"))
    @MapKeyColumn(name = "guest_id")
    @Column(name = "rating")
    private Map<Long, HotelRatingEntity> ratings;*/

    // Default constructor for JPA
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

    // Getters and setters
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

    // Builder Class
    public static class HotelBuilder {
        private Long id;
        private String name;
        private String description;
        private HotelLocationEntity location;
        private double averageRating = 0.0; // Default rating
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


        public HotelEntity build() {
            return new HotelEntity(this);
        }
    }
}

package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "hotel_ratings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"hotel_id", "guest_id"}) // Enforce one rating per guest per hotel
)
public class HotelRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "star_rating", nullable = false)
    private int starRating;

    @Column(name = "comment_rating")
    private String commentRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private GuestEntity guest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    // Default constructor for JPA
    protected HotelRatingEntity() {}

    private HotelRatingEntity(Builder builder) {
        this.starRating = builder.rating;
        this.commentRating = builder.commentRating;
        this.id = builder.id;
        this.hotel = builder.hotel;
        this.guest = builder.guest;
        this.booking = builder.booking;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public int getStarRating() {
        return starRating;
    }

    public String getCommentRating() {
        return commentRating;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public GuestEntity getGuest() {
        return guest;
    }

    public void setGuest(GuestEntity guest) {
        this.guest = guest;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    // Builder Class
    public static class Builder {
        private int rating; // Required field
        private String commentRating = ""; // Optional, default value
        private Long id;
        private HotelEntity hotel;
        private GuestEntity guest;
        private BookingEntity booking;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder withComment(String commentRating) {
            this.commentRating = commentRating;
            return this;
        }

        public Builder withHotel(HotelEntity hotel) {
            this.hotel = hotel;
            return this;
        }

        public Builder withGuest(GuestEntity guest) {
            this.guest = guest;
            return this;
        }

        public Builder withBooking(BookingEntity booking) {
            this.booking = booking;
            return this;
        }

        public HotelRatingEntity build() {
            return new HotelRatingEntity(this);
        }
    }
}

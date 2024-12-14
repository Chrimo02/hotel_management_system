package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRating;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "hotel_ratings")
public class HotelRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Maps the enum as a string in the database
    @Column(name = "star_rating", nullable = false)
    private int starRating;

    @Column(name = "comment_rating")
    private String commentRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false) // Ensure this maps to the correct column
    private HotelEntity hotel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;


    // Default constructor for JPA
    protected HotelRatingEntity() {}

    private HotelRatingEntity(Builder builder) {
        this.starRating = builder.rating;
        this.commentRating = builder.commentRating;
        this.id = builder.id;
        this.booking = builder.booking;
        this.hotel = builder.hotel;
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

    public BookingEntity getBooking(){
        return this.getBooking();
    }

    // Builder Class
    public static class Builder {
        private int rating; // Pflichtfeld
        private String commentRating = ""; // Optional, Standardwert
        private Long id;
        private BookingEntity booking;
        private HotelEntity hotel;
        // Pflichtfeld-Methode

        public HotelRatingEntity.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public HotelRatingEntity.Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        // Optionales Feld
        public HotelRatingEntity.Builder withComment(String commentRating) {
            this.commentRating = commentRating;
            return this;
        }

        public HotelRatingEntity.Builder withBooking(BookingEntity booking) {
            this.booking = booking;
            return this;
        }


        public HotelRatingEntity.Builder withHotel(HotelEntity hotel) {
            this.hotel = hotel;
            return this;
        }


        // Build-Methode zum Erstellen eines HotelRating-Objekts
        public HotelRatingEntity build() {

            return new HotelRatingEntity(this);
        }
    }
}

package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
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

    // Default constructor for JPA
    protected HotelRatingEntity() {}

    private HotelRatingEntity(Builder builder) {
        this.starRating = builder.starRating;
        this.commentRating = builder.commentRating;
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

    // Builder Class
    public static class Builder {
        private int starRating;
        private String commentRating;
        private HotelEntity hotelEntity;

        public Builder(HotelEntity hotelEntity){
            this.hotelEntity = hotelEntity;
        }

        // Builder method for starRating (mandatory field)
        public Builder withStarRating(int starRating) {
            this.starRating = starRating;
            return this;
        }

        // Builder method for commentRating (optional field)
        public Builder withCommentRating(String commentRating) {
            this.commentRating = commentRating;
            return this;
        }

        // Build method to create the instance
        public HotelRatingEntity build() {

            return new HotelRatingEntity(this);
        }
    }
}

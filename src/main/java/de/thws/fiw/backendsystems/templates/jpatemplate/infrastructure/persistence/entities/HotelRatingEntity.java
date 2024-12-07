package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import jakarta.persistence.*; 
import java.util.UUID;

@Entity
@Table(name = "hotel_ratings")
public class HotelRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "star_rating", nullable = false)
    private HotelRatingEntity starRating;

    @Column(name = "comment_rating")
    private String commentRating;

    protected HotelRatingEntity() {
        // Default-Konstruktor für JPA
    }

    public HotelRatingEntity(HotelRatingEntity starRating, String commentRating) {
        this.starRating = starRating;
        this.commentRating = commentRating;
    }

    public Long getId() {
        return id;
    }

    public HotelRatingEntity getStarRating() {
        return starRating;
    }

    public void setStarRating(HotelRatingEntity starRating) {
        this.starRating = starRating;
    }

    public String getCommentRating() {
        return commentRating;
    }

    public void setCommentRating(String commentRating) {
        this.commentRating = commentRating;
    }

    @Override
    public String toString() {
        return "HotelRatingEntity{" +
                "id=" + id +
                ", starRating=" + starRating +
                ", commentRating='" + commentRating + '\'' +
                '}';
    }
}

package hotelmanagementsystem.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "hotel_ratings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"hotel_id", "guest_id"})
)
public class HotelRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "star_rating", nullable = false)
    private int starRating;

    @Column(name = "comment_rating")
    private String commentRating;

    @Column(name = "hotel_id", insertable = false, updatable = false)
    private Long hotelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private GuestEntity guest;

    protected HotelRatingEntity() {}

    private HotelRatingEntity(Builder builder) {
        this.id = builder.id;
        this.starRating = builder.rating;
        this.commentRating = builder.commentRating;
        this.guest = builder.guest;
    }

    public Long getId() {
        return id;
    }

    public int getStarRating() {
        return starRating;
    }

    public String getCommentRating() {
        return commentRating;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public GuestEntity getGuest() {
        return guest;
    }

    public static class Builder {
        private Long id;
        private int rating;
        private String commentRating = "";
        private GuestEntity guest;

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

        public Builder withGuest(GuestEntity guest) {
            this.guest = guest;
            return this;
        }

        public HotelRatingEntity build() {
            return new HotelRatingEntity(this);
        }
    }
}

package hotelmanagementsystem.domain.models;

public class HotelRating {

    private final Long id;
    private final Hotel hotel;
    private final Guest guest;
    private final int rating;
    private final String commentRating;

    private HotelRating(Builder builder) {
        this.id = builder.id;
        this.hotel = builder.hotel;
        this.guest = builder.guest;
        this.rating = builder.rating;
        this.commentRating = builder.commentRating;
    }

    public Long getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Guest getGuest() {
        return guest;
    }

    public int getStarRating() {
        return rating;
    }

    public String getGuestComment() {
        return commentRating;
    }

    public static class Builder {
        private Long id;
        private Hotel hotel;
        private Guest guest;
        private int rating;
        private String commentRating = "";

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withHotel(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }

        public Builder withGuest(Guest guest) {
            this.guest = guest;
            return this;
        }

        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder withComment(String comment) {
            this.commentRating = comment;
            return this;
        }

        public HotelRating build() {
            return new HotelRating(this);
        }
    }
}

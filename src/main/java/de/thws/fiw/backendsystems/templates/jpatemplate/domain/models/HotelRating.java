package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

public class HotelRating {

    private Long id;
    private String commentRating;
    private int rating;
    private Booking booking;

    private HotelRating(Builder builder) {
        this.rating = builder.rating;
        this.commentRating = builder.commentRating;
        this.id = builder.id;
        this.booking = builder.booking;
    }

    // Getter für die Sternebewertung
    public int getStarRating() {
        return rating;
    }


    public Long getId() {
        return id;
    }

    public Booking getBooking(){
        return booking;
    }

    //Getter for the comment
    public String getGuestComment() {
        return commentRating;
    }

    public boolean setCommentRating(String commentRating) {
        this.commentRating = commentRating;
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }


    // Methode zur Ausgabe der Sternebewertung
    public void displayRating() {
        System.out.println("Hotel Rating: " + rating + " Stars");
        if (!commentRating.isEmpty()) {
            System.out.println("Guest Comment: " + commentRating);
        }
    }

    public static class Builder {
        private int rating; // Pflichtfeld
        private String commentRating = ""; // Optional, Standardwert
        private Long id;
        private Booking booking;
        // Pflichtfeld-Methode

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        // Optionales Feld
        public Builder withComment(String commentRating) {
            this.commentRating = commentRating;
            return this;
        }

        public Builder withBooking(Booking booking) {
            this.booking = booking;
            return this;
        }

        // Build-Methode zum Erstellen eines HotelRating-Objekts
        public HotelRating build() {

            return new HotelRating(this);
        }
    }
}

package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

public class HotelRating {

    private String commentRating;
    private int rating;
    private Booking booking;

    private HotelRating(int rating) {
        this.rating = rating;
        this.commentRating = "";
    }

    // Getter für die Sternebewertung
    public int getStarRating() {
        return rating;
    }

    //Getter for the comment
    public String getGuestComment() {
        return commentRating;
    }

    public boolean setCommentRating(String commentRating){
        this.commentRating = commentRating;
        return true;
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

        // Pflichtfeld-Methode
        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        // Optionales Feld
        public Builder withComment(String commentRating) {
            this.commentRating = commentRating;
            return this;
        }

        // Build-Methode zum Erstellen eines HotelRating-Objekts
        public HotelRating build() {

            return new HotelRating(this);
        }
}

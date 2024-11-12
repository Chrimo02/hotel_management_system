package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

public enum HotelRating {
    ONE_STAR(1),
    TWO_STAR(2),
    THREE_STAR(3),
    FOUR_STAR(4),
    FIVE_STAR(5);

    private final int starRating;
    private String commentRating;

    // Konstruktor für die Sternebewertung
    HotelRating(int starRating) {
        this.starRating = starRating;
        this.commentRating = "";
    }

    // Getter für die Sternebewertung
    public int getStarRating() {
        return starRating;
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
        System.out.println("Hotel Rating: " + starRating + " Stars");
        if (!commentRating.isEmpty()) {
            System.out.println("Guest Comment: " + commentRating);
        }
    }
}




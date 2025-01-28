package hotelmanagementsystem.infrastructure.api.dto;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;

public class HotelRatingDTO {

    private long id;
    private int rating;          // Sternebewertung (z.B. 1..5)
    private String commentRating;
    private long bookingId;      // ID der zugehörigen Buchung
    private long guestId;        // ID des Gasts

    // --- Getter & Setter ---

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCommentRating() {
        return commentRating;
    }
    public void setCommentRating(String commentRating) {
        this.commentRating = commentRating;
    }

    public long getBookingId() {
        return bookingId;
    }
    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getGuestId() {
        return guestId;
    }
    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    // --- Konvertierung DTO -> gRPC (Protobuf) ---
//    public HotelRating toProtobuf() {
//        return new HotelRating.Builder()
//                .withId(this.id)
//                .withRating(this.rating)
//                .withComment(this.commentRating != null ? this.commentRating : "")
//                .withBooking(this.bookingId)
//                .withGuestId(this.guestId)
//                .build();
//    }
}

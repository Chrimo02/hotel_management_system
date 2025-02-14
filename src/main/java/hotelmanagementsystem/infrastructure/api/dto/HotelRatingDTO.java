package hotelmanagementsystem.infrastructure.api.dto;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;

public class HotelRatingDTO {

    private long id;
    private int rating;
    private String comment;
    private long guestId;

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

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getGuestId() {
        return guestId;
    }
    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public HotelRating toProtobuf() {
        return HotelRating.newBuilder()
                .setStarRating(this.getRating())
                .setComment(this.getComment())
                .build();
    }
}

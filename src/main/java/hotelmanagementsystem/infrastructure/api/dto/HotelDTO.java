package hotelmanagementsystem.infrastructure.api.dto;
import java.util.List;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Hotel;

public class HotelDTO {

    private long id;
    private String name;
    private String description;
    private double averageRating;
    private List<Long> roomIds;
    private List<Long> bookingIds;
    private List<Long> hotelRatingIds;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Long> getRoomIds() {
        return roomIds;
    }
    public void setRoomIds(List<Long> roomIds) {
        this.roomIds = roomIds;
    }

    public List<Long> getBookingIds() {
        return bookingIds;
    }
    public void setBookingIds(List<Long> bookingIds) {
        this.bookingIds = bookingIds;
    }

    public List<Long> getHotelRatingIdsds() {
        return hotelRatingIds;
    }
    public void setHotelRatingIds(List<Long> hotelRatingIds) {
        this.hotelRatingIds = hotelRatingIds;
    }

    public Hotel toProtobuf() {
        return Hotel.newBuilder()
                .setId(this.id)
                .setName(this.name)
                .setDescription(this.description)
                .setAverageRating(this.averageRating)
                .addAllRoomIds(this.roomIds != null ? this.roomIds : List.of())
                .addAllBookingIds(this.bookingIds != null ? this.bookingIds : List.of())
                .addAllHotelRatingIds(this.hotelRatingIds != null ? this.hotelRatingIds : List.of())
                .build();
    }
}

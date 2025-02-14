package hotelmanagementsystem.infrastructure.api.dto;
import java.util.List;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Hotel;
import hotelmanagementsystem.infrastructure.api.grpc.generated.HotelLocation;

public class HotelDTO {

    private long id;
    private String name;
    private String description;
    private double averageRating;
    private HotelLocationDTO hotelLocation;
    private List<Long> roomIds;
    private List<Long> bookingIds;
    private List<HotelRatingDTO> hotelRatings;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public HotelLocationDTO getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(HotelLocationDTO hotelLocation) {
        this.hotelLocation = hotelLocation;
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

    public List<HotelRatingDTO> getHotelRatings() {
        return hotelRatings;
    }

    public void setHotelRatings(List<HotelRatingDTO> hotelRatings) {
        this.hotelRatings = hotelRatings;
    }

    public Hotel toProtobuf() {
        return Hotel.newBuilder()
                .setId(this.id)
                .setName(this.name)
                .setDescription(this.description)
                .setAverageRating(this.averageRating)
                .addAllRoomIds(this.roomIds != null ? this.roomIds : List.of())
                .addAllBookingIds(this.bookingIds != null ? this.bookingIds : List.of())
                .addAllHotelRatings(hotelRatings.stream().map(HotelRatingDTO::toProtobuf).toList())
                .build();
    }
}

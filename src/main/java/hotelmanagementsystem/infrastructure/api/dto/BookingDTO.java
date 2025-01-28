package hotelmanagementsystem.infrastructure.api.dto;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Booking;
import java.time.LocalDate;
import java.util.List;

public class BookingDTO {
    private long id;
    private long hotelId;
    private List<Long> guestIds;    // All guest IDs in the booking
    private List<Long> roomIds;     // The final assigned room IDs
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    // --- Getters and Setters ---
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getHotelId() {
        return hotelId;
    }
    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public List<Long> getGuestIds() {
        return guestIds;
    }
    public void setGuestIds(List<Long> guestIds) {
        this.guestIds = guestIds;
    }

    public List<Long> getRoomIds() {
        return roomIds;
    }
    public void setRoomIds(List<Long> roomIds) {
        this.roomIds = roomIds;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    // --- Convert DTO to gRPC Booking (proto) ---
    public Booking toProtobuf() {
        return Booking.newBuilder()
                .setId(this.id)
                .setHotelId(this.hotelId)
                .addAllGuestIds(this.guestIds)   // multiple guests
                .addAllRoomIds(this.roomIds)     // final assigned rooms
                .setCheckInDate(this.checkInDate.toString())
                .setCheckOutDate(this.checkOutDate.toString())
                .build();
    }
}

package hotelmanagementsystem.infrastructure.api.dto;

import hotelmanagementsystem.infrastructure.api.grpc.generated.Booking;
import java.time.LocalDate;
import java.util.List;

public class BookingDTO {
    private long id;
    private long hotelId;
    private long guestId;
    private List<Long> roomIds;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    // Getters and setters
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

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
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

    // Convert DTO to gRPC Booking object
    public Booking toProtobuf() {
        return Booking.newBuilder()
                .setId(this.id)
                .setHotelId(this.hotelId)
                .setGuestId(this.guestId)
                .addAllRoomIds(this.roomIds)
                .setCheckInDate(this.checkInDate.toString())
                .setCheckOutDate(this.checkOutDate.toString())
                .build();
    }
}

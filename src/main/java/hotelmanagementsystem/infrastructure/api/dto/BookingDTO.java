package hotelmanagementsystem.infrastructure.api.dto;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Booking;
import hotelmanagementsystem.infrastructure.api.grpc.generated.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BookingDTO {
    private long id;
    private long hotelId;
    private List<Long> guestIds;
    private List<Long> roomIds;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean status;
    private double totalPrice;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;


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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Booking toProtobuf() {
        BookingStatus bookingStatus = status ? BookingStatus.ACTIVE : BookingStatus.CANCELED;

        Booking.Builder builder = Booking.newBuilder()
                .setId(this.id)
                .setHotelId(this.hotelId)
                .addAllGuestIds(this.guestIds)
                .addAllRoomIds(this.roomIds)
                .setCheckInDate(this.checkInDate.toString())
                .setCheckOutDate(this.checkOutDate.toString())
                .setStatus(bookingStatus)
                .setTotalPrice(this.totalPrice);

        if (this.checkInTime != null) {
            builder.setCheckInTime(this.checkInTime.toString());
        }

        if (this.checkOutTime != null) {
            builder.setCheckOutTime(this.checkOutTime.toString());
        }

        return builder.build();
    }
}

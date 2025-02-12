package hotelmanagementsystem.infrastructure.api.dto;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Room;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDTO {
    private long id;
    private double pricePerNight;
    private RoomIdentifierDTO roomIdentifier;
    private long hotelId;
    private List<BookingDTO> bookings;
    private String type;

    public RoomDTO(long id, double pricePerNight, RoomIdentifierDTO roomIdentifier, long hotelId, List<BookingDTO> bookings, String type) {
        this.id = id;
        this.pricePerNight = pricePerNight;
        this.roomIdentifier = roomIdentifier;
        this.hotelId = hotelId;
        this.bookings = bookings;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomIdentifierDTO getRoomIdentifierDTO() {
        return roomIdentifier;
    }

    public void setRoomIdentifierDTO(RoomIdentifierDTO roomIdentifier) {
        this.roomIdentifier = roomIdentifier;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public List<BookingDTO> getBookingDTOs() {
        return bookings;
    }

    public void setBookingDTOs(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Room toProtobuf() {
        return Room.newBuilder()
                .setId(this.getId())
                .setPricePerNight(this.getPricePerNight())
                .setType(this.getType())
                .setRoomIdentifier(this.getRoomIdentifierDTO().toProtobuf())
                .setHotelId(this.hotelId)
                .addAllBookings(bookings.stream().map(BookingDTO::toProtobuf).collect(Collectors.toList()))
                .build();
    }
}

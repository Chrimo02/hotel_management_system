//package hotelmanagementsystem.infrastructure.api.dto;
//
//import hotelmanagementsystem.domain.models.Booking;
//import hotelmanagementsystem.domain.models.Hotel;
//import hotelmanagementsystem.domain.models.RoomIdentifier;
//import hotelmanagementsystem.infrastructure.api.grpc.generated.Guest;
//import hotelmanagementsystem.infrastructure.api.grpc.generated.Room;
//
//import java.util.Set;
//import java.util.TreeSet;
//
//public class RoomDTO {
//    private long id;
//    private double pricePerNight;
//    private RoomIdentifier roomIdentifier; //dto
//    private Hotel hotel; //id
//    private Set<Booking> bookings = new TreeSet<>(); // TreeSet für sortierte Buchungen
//
//    protected RoomDTO(long id, double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
//        this.id = id;
//        this.pricePerNight = pricePerNight;
//        this.roomIdentifier = roomIdentifier;
//        this.hotel = hotel;
//    }
//
//    public Set<Booking> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(Set<Booking> bookings) {
//        this.bookings = bookings;
//    }
//
//    public double getPricePerNight() {
//        return pricePerNight;
//    }
//
//    public long getId() {
//        return id;
//    }
//    public RoomIdentifier getRoomIdentifier() {
//        return roomIdentifier;
//    }
//    public void setRoomIdentifier(RoomIdentifier roomIdentifier) {
//        this.roomIdentifier = roomIdentifier;
//    }
//    public void setPricePerNight(double pricePerNight) {
//        this.pricePerNight = pricePerNight;
//    }
//
//    public Hotel getHotel() {
//        return hotel;
//    }
//
//    public void setHotel(Hotel hotel) {
//        this.hotel = hotel;
//    }
//
//    public Room toProtobuf(){
//        return Room.newBuilder()
//                .setId(this.id)
//                .setPricePerNight(this.pricePerNight)
//                .setType(this.roomIdentifier.toString())
//                .setHotelId(this.hotel.getId())
//                .setBookings(this.bookings)
//                .build();
//    }
//}

package hotelmanagementsystem.infrastructure.api.dto;
import java.util.List;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Hotel;
/**
 * DTO-Klasse für Hotel-Daten.
 * Angelehnt an die Struktur von BookingDTO,
 * inkl. einer toProtobuf()-Methode für gRPC.
 */
public class HotelDTO {

    private long id;
    private String name;
    private String description;
    private double averageRating;
    private List<Long> roomIds;
    private List<Long> bookingIds;

    // --- Getter / Setter ---

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

    // --- Konvertierung DTO -> gRPC (Protobuf) ---
    public Hotel toProtobuf() {
        // Annahme: Dein Proto 'Hotel' hat Felder wie:
        //   int64 id, string name, string description, double averageRating
        //   repeated int64 roomIds, repeated int64 bookingIds
        return Hotel.newBuilder()
                .setId(this.id)
                .setName(this.name)
                .setDescription(this.description)
                .setAverageRating(this.averageRating)
                .addAllRoomIds(this.roomIds != null ? this.roomIds : List.of())
                .addAllBookingIds(this.bookingIds != null ? this.bookingIds : List.of())
                .build();
    }
}

package hotelmanagementsystem.infrastructure.api.dto;
import hotelmanagementsystem.infrastructure.api.grpc.generated.HotelLocation;
public class HotelLocationDTO {

    private long id;
    private String address;
    private String city;
    private String country;

    // --- Getter & Setter ---
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    // --- Konvertierung DTO -> gRPC (Protobuf) ---
    public HotelLocation toProtobuf() {
        return HotelLocation.newBuilder()
                .setId(this.id)
                .setAddress(this.address != null ? this.address : "")
                .setCity(this.city != null ? this.city : "")
                .setCountry(this.country != null ? this.country : "")
                .build();
    }
}

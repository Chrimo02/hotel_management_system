package hotelmanagementsystem.infrastructure.api.dto;
import hotelmanagementsystem.infrastructure.api.grpc.generated.HotelLocation;
public class HotelLocationDTO {

    private String address;
    private String city;
    private String country;

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

    public HotelLocation toProtobuf() {
        return HotelLocation.newBuilder()
                .setAddress(this.address != null ? this.address : "")
                .setCity(this.city != null ? this.city : "")
                .setCountry(this.country != null ? this.country : "")
                .build();
    }
}

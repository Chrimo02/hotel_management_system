package hotelmanagementsystem.domain.models;

import hotelmanagementsystem.infrastructure.api.mapper.HotelLocationMapper;

public class HotelLocation {
    private Long id;
    private String address;
    private String city;
    private String country;

    private HotelLocation(HotelLocationBuilder hotelLocationBuilder) {
        this.id = hotelLocationBuilder.id;
        this.address = hotelLocationBuilder.address;
        this.city = hotelLocationBuilder.city;
        this.country = hotelLocationBuilder.country;
    }

    public Long getId(){ return id; }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }

    public void setId(Long id) { this.id = id; }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public static class HotelLocationBuilder {
        private Long id;
        private String address;
        private String city;
        private String country;


        public HotelLocationBuilder() {}

        public HotelLocationBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public HotelLocationBuilder withAddress(String address){
            this.address = address;
            return this;
        }

        public HotelLocationBuilder withCity(String city){
            this.city = city;
            return this;
        }

        public HotelLocationBuilder withCountry(String country){
            this.country = country;
            return this;
        }

        public HotelLocation build(){
            return new HotelLocation(this);
        }
    }
}

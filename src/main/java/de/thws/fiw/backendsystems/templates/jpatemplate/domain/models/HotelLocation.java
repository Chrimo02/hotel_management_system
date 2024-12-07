package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

import java.util.UUID;

public class HotelLocation {
    private final long id;
    private String address;
    private String city;
    private String country;

    private HotelLocation(HotelLocationBuilder hotelLocationBuilder) {
        this.address = hotelLocationBuilder.address;
        this.city = hotelLocationBuilder.city;
        this.country = hotelLocationBuilder.country;
    }

    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }

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
        private String address;
        private String city;
        private String country;

        public HotelLocationBuilder(){
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

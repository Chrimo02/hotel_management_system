package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel_locations")
public class HotelLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    protected HotelLocationEntity() {
    }

    private HotelLocationEntity(HotelLocationBuilder builder) {
        this.address = builder.address;
        this.city = builder.city;
        this.country = builder.country;
    }

    public Long getId() {
        return id;
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

        public HotelLocationBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public HotelLocationBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public HotelLocationBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public HotelLocationEntity build() {
            return new HotelLocationEntity(this);
        }
    }
}

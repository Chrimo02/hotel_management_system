package hotelmanagementsystem.infrastructure.persistence.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "room_type", discriminatorType = DiscriminatorType.STRING)
public abstract class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double pricePerNight;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_identifier_id")
    private RoomIdentifierEntity roomIdentifier;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BookingEntity> bookings = new HashSet<>();

    public RoomEntity() {

    }
    public RoomEntity(long id, double pricePerNight, RoomIdentifierEntity roomIdentifierEntity, HotelEntity hotelEntity) {
        this.id = id;
        this.pricePerNight = pricePerNight;
        this.roomIdentifier = roomIdentifierEntity;
        this.hotel = hotelEntity;


    }
    public RoomEntity(double pricePerNight, RoomIdentifierEntity roomIdentifierEntity, HotelEntity hotelEntity) {
        this.pricePerNight = pricePerNight;
        this.roomIdentifier = roomIdentifierEntity;
        this.hotel = hotelEntity;

    }

    public long getId() {
        return id;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomIdentifierEntity getRoomIdentifier() {
        return roomIdentifier;
    }

    public void setRoomIdentifier(RoomIdentifierEntity roomIdentifier) {
        this.roomIdentifier = roomIdentifier;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingEntity> bookings) {
        this.bookings = bookings;
    }
}

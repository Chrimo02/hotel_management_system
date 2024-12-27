package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotel;

    @Column (nullable = false)
    private LocalDate checkInDate;
    @Column (nullable = false)
    private LocalDate checkOutDate;

    @ManyToMany
    @JoinTable(
            name = "booking_guest", // Name der Zwischentabelle
            joinColumns = @JoinColumn(name = "booking_id"), // FK zur Booking-Tabelle
            inverseJoinColumns = @JoinColumn(name = "guest_id") // FK zur Guest-Tabelle
    )
    private List<GuestEntity> guests;

    @ManyToMany
    @JoinTable(
            name = "booking_room",
            joinColumns = @JoinColumn(name = "booking_id"),  // Foreign key to the BookingEntity
            inverseJoinColumns = @JoinColumn(name = "room_id")  // Foreign key to the RoomEntity
    )
    private List<RoomEntity> rooms;


    @Column (nullable = false)
    private boolean status;
    @Column (nullable = true)
    private LocalDateTime checkInTime;
    @Column (nullable = true)
    private LocalDateTime checkOutTime;

    public BookingEntity() {}

    public BookingEntity(HotelEntity hotel, LocalDate checkInDate, LocalDate checkOutDate, List<RoomEntity> rooms, List<GuestEntity> guests) {
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rooms = rooms;
        this.guests = guests;
        this.status = true;
    }

    public BookingEntity(long id, HotelEntity hotel, LocalDate checkInDate, LocalDate checkOutDate, List<RoomEntity> rooms, List<GuestEntity> guests, boolean status, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.id = id;
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rooms = rooms;
        this.guests = guests;
        this.status = status;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public long getId() {
        return id;
    }

    public HotelEntity getHotel() { return hotel; }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public List<GuestEntity> getGuests() {
        return guests;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public boolean isStatus() {
        return status;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }
}

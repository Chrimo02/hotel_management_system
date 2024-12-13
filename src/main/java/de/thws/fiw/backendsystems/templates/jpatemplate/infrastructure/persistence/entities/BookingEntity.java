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
    @Column (nullable = false)
    private long hotelId; //long oder HotelEntity oder evtl gar nicht speichern?
    @Column (nullable = false)
    private LocalDate checkInDate;
    @Column (nullable = false)
    private LocalDate checkOutDate;

    // beziehung mit Guest ---> Buchung hat mehrere Gäste und ein Gast hat mehrere Buchungen
    @ManyToMany
    @JoinTable(
            name = "booking_guest", // Name der Zwischentabelle
            joinColumns = @JoinColumn(name = "booking_id"), // FK zur Booking-Tabelle
            inverseJoinColumns = @JoinColumn(name = "guest_id") // FK zur Guest-Tabelle
    )
    //Wofür: @JoinColumn(name = "bookingsHistory") // Name der Fremdschlüsselspalte
    private List<GuestEntity> guests;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<RoomEntity> rooms;

    @Column (nullable = false)
    private boolean status;
    @Column (nullable = true)
    private LocalDateTime checkInTime;
    @Column (nullable = true)
    private LocalDateTime checkOutTime;

    public BookingEntity(long hotelId, LocalDate checkInDate, LocalDate checkOutDate, List<RoomEntity> rooms, List<GuestEntity> guests) {
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rooms = rooms;
        this.guests = guests;
        this.status = true;
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

    public long getHotelId() {
        return hotelId;
    }

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

package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    // beziehung mit Guest ---> Buchung hat mehrere Gäste und ein Gast hat mehrere Buchungen
    @ManyToMany
    @JoinTable(
            name = "booking_guest", // Name der Zwischentabelle
            joinColumns = @JoinColumn(name = "booking_id"), // FK zur Booking-Tabelle
            inverseJoinColumns = @JoinColumn(name = "guest_id") // FK zur Guest-Tabelle
    )
    @JoinColumn(name = "bookingsHistory") // Name der Fremdschlüsselspalte
    private List<GuestEntity> guests;
}

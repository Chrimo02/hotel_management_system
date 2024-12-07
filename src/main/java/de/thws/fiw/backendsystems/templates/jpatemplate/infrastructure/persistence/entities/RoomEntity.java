package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.RoomIdentifier;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
@Entity
@Table(name = "room")
public class RoomEntity {
    //einzelne Room-types mit Vererbung implementieren
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private long id;
    private double pricePerNight;
    @OneToOne
    private RoomIdentifierEntity roomIdentifier;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
    @OneToOne
    private BookingEntity currentBooking;

    // muss die map gespeichert werden?
    private Map<LocalDate, Boolean> availabilityMap = new HashMap<>();
    public RoomEntity() {

    }
    public RoomEntity(long id, double pricePerNight, RoomIdentifierEntity roomIdentifierEntity, HotelEntity hotelEntity) {
        this.id = id;
        this.pricePerNight = pricePerNight;
        this.roomIdentifier = roomIdentifierEntity;
        this.hotel = hotelEntity;

        // Initialize the availability for two years from today
        //initializeAvailability();
    }
    public RoomEntity(double pricePerNight, RoomIdentifierEntity roomIdentifierEntity, HotelEntity hotelEntity) {
        this.pricePerNight = pricePerNight;
        this.roomIdentifier = roomIdentifierEntity;
        this.hotel = hotelEntity;

        // Initialize the availability for two years from today
        //initializeAvailability();
    }

    /*private void initializeAvailability() {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 730; i++) {
            availabilityMap.put(today.plusDays(i), true); // Initially, all days are available
        }
    }*/

    //TODO: cleanupolddates in roomservice/hotelservice und scheduler? wöchentlich/monatlich aufruf von allen räumen
   /* ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
scheduler.scheduleAtFixedRate(() -> {
        for (Room room : hotel.getRooms()) {
            room.cleanupOldDates();
        }
    }, 0, 1, TimeUnit.DAYS); // Führt das tägliche Update durch*/


    public void cleanupOldDates() {
        LocalDate today = LocalDate.now();

        // Entfernt alle vergangenen Daten
        availabilityMap.keySet().removeIf(date -> date.isBefore(today));

        // Fügt neue Daten für zwei Jahre im Voraus hinzu
        for (int i = 0; i < 730; i++) {
            LocalDate futureDate = today.plusDays(i);
            availabilityMap.putIfAbsent(futureDate, true); // Nur hinzufügen, falls das Datum noch nicht vorhanden ist
        }
    }


    public BookingEntity getCurrentBooking() {
        return currentBooking;
    }
    /*public Map<LocalDate, Boolean> getAvailabilityMap() {
        return availabilityMap;
    }*/
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
}
package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;
import java.util.HashMap;
import java.util.Map;

import java.time.LocalDate;

public abstract class Room {

    //einzelne Room-types mit Vererbung implementieren
    private long id;
    private double pricePerNight;
    private RoomIdentifier roomIdentifier;
    private Hotel hotel;
    private Booking currentBooking; //benötigt?

    // Availability map for specific dates
    private Map<LocalDate, Boolean> availabilityMap = new HashMap<>();

    public Room(long id, double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
        this.id = id;
        this.pricePerNight = pricePerNight;
        this.roomIdentifier = roomIdentifier;
        this.hotel = hotel;

        // Initialize the availability for two years from today
        initializeAvailability();
    }
    public Room(double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
        this.pricePerNight = pricePerNight;
        this.roomIdentifier = roomIdentifier;
        this.hotel = hotel;

        // Initialize the availability for two years from today
        initializeAvailability();
    }

    private void initializeAvailability() {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 730; i++) {
            availabilityMap.put(today.plusDays(i), true); // Initially, all days are available
        }
    }

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


    public Booking getCurrentBooking() {
        return currentBooking;
    }
    public Map<LocalDate, Boolean> getAvailabilityMap() {
        return availabilityMap;
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

    public RoomIdentifier getRoomIdentifier() {
        return roomIdentifier;
    }

    public void setRoomIdentifier(RoomIdentifier roomIdentifier) {
        this.roomIdentifier = roomIdentifier;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}

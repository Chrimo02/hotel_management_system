package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private final long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate; //final oder nicht? Verlängerung?
    private double totalPrice;
    private Room room;
    private List<Guest> guests;
    private HotelRating rating; //wird in der Booking-Klasse nicht weiter benutzt, lediglich von der zugehörigen Methode im BookingService in der jeweiligen Buchung gespeichert
    private boolean status; // shows true if the booking is confirmed and active or false if the booking was canceled --> true after initializing

    // Private constructor for the builder pattern
    private Booking(Builder builder) {
        this.id = builder.id;
        this.checkInDate = builder.checkInDate;
        this.checkOutDate = builder.checkOutDate;
        this.room = builder.room;
        this.guests = builder.guests;
        this.totalPrice = calculateTotalPrice();
        this.status = true;
    }

    public void setStatus(boolean b){
        this.status = b;
    }

    public boolean getStatus(){
        return this.status;
    }

    public HotelRating getRating() {
        return rating;
    }

    public void setRating(HotelRating rating) {
        this.rating = rating;
    }

    public static long calculateNights(LocalDate checkInDate, LocalDate checkOutDate) {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }
    private double calculateTotalPrice(){
        long nights = calculateNights(getCheckInDate(), getCheckOutDate());
        return this.room.getPricePerNight()*nights;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Room getRoom() {
        return room;
    }

    public List<Guest> getGuests() {
        return guests;
    }

}

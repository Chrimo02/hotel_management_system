package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Booking {
    private final long id; //final
    private LocalDate checkInDate;
    private LocalDate checkOutDate; //final oder nicht? Verlängerung?
    private double totalPrice;
    private List<Room> rooms;
    private List<Guest> guests;
    private HotelRating rating; //wird in der Booking-Klasse nicht weiter benutzt, lediglich von der zugehörigen Methode im BookingService in der jeweiligen Buchung gespeichert
    private boolean status; // shows true if the booking is confirmed and active or false if the booking was canceled --> true after initializing
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private boolean checkedIn;
    private boolean checkedOut;

    // Private constructor for the builder pattern
    public Booking(long id, LocalDate checkInDate,LocalDate checkOutDate, List<Room> rooms, Guest ... guests) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rooms = rooms;
        for (Guest g : guests) this.guests.add(g);
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
        double price = 0;
        for (Room room : rooms) {
            price += room.getPricePerNight() * nights;
        }
        return price;
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public List<Guest> getGuests() {
        return guests;
    }

}

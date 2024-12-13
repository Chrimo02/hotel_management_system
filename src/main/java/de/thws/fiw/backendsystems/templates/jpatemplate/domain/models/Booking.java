package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class Booking {
    private final long id; //final
    private final long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate; //final oder nicht? Verlängerung?
    private double totalPrice;
    private List<Room> rooms;
    private List<Guest> guests;
    private boolean status; // shows true if the booking is confirmed and active or false if the booking was canceled --> true after initializing
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public Booking(long id, long hotelId, LocalDate checkInDate,LocalDate checkOutDate, List<Room> rooms, List<Guest> guests, boolean status, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.id = id;
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rooms = rooms;
        this.guests = guests;
        this.totalPrice = calculateTotalPrice();
        this.status = status;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public void setStatus(boolean b){
        this.status = b;
    }

    public boolean getStatus(){
        return this.status;
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

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public boolean isCheckedIn() {
        return checkInTime != null; // Checked in if checkInTime is not null
    }

    public boolean isCheckedOut() {
        return checkOutTime != null; // Checked out if checkOutTime is not null
    }
}

package hotelmanagementsystem.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Booking implements Comparable<Booking> {
    private final long id;
    private final Hotel hotel;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final double totalPrice;
    private List<Room> rooms;
    private final List<Guest> guests;
    private boolean status;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public Booking(long id, Hotel hotel, LocalDate checkInDate,LocalDate checkOutDate, List<Room> rooms, List<Guest> guests, boolean status, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.id = id;
        this.hotel = hotel;
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
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Check-in and check-out dates must not be null");
        }
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
    public Hotel getHotel() { return hotel; }

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
        return checkInTime != null;
    }

    public boolean isCheckedOut() {
        return checkOutTime != null;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int compareTo(Booking other) {
        return this.checkInDate.compareTo(other.checkInDate);

    }
}

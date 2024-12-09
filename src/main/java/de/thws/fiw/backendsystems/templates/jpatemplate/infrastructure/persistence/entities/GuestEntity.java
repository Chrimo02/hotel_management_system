package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false)
    private String firstName;
    @Column (nullable = false)
    private String lastName;
    @Column (nullable = true)
    private String title;
    @Column (nullable = false)
    private LocalDate birthday;
    @Column (nullable = false)
    private String eMail;
    @Column (nullable = false)
    private String phoneNumber;
    @ManyToMany (mappedBy = "guests")
    private List<BookingEntity> bookingsHistory;

    public GuestEntity(String firstName, String lastName, String title, int yearBirthday,int monthBirtday, int dayBirthday, String eMail, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.birthday = LocalDate.of(yearBirthday,monthBirtday,dayBirthday);
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }
    public GuestEntity(){};

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<BookingEntity> getBookingsHistory() {
        return bookingsHistory;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBookingsHistory(List<BookingEntity> bookingsHistory) {
        this.bookingsHistory = bookingsHistory;
    }
}

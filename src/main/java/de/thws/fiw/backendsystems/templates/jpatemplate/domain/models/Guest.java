package de.thws.fiw.backendsystems.templates.jpatemplate.domain.models;
import java.time.LocalDate;
import java.util.List;

public class Guest {
    private final long id;
    private String firstName;
    private String lastName;
    private String title;
    private LocalDate birthday;
    private String eMail;
    private String phoneNumber;
    private List<Booking> bookingsHistory;  // --> aufpassen, dass beim Erstellen jeder Buchung auch die Buchung zur History hinzugefügt wird

    public Guest(long id, String firstName, String lastName, String title, int yearBirthday,int monthBirtday, int dayBirthday, String eMail, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.birthday = LocalDate.of(yearBirthday,monthBirtday,dayBirthday);
        this.id = id;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }

    public List<Booking> getBookingsHistory(){
        return this.bookingsHistory;
    }

    public long getId() {
        return id;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

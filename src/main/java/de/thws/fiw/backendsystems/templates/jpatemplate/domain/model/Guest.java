package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

public class Guest extends Person {
    private final long id;
    private String eMail;
    private String phoneNumber;
    private List<Booking> bookingsHistory;  // --> aufpassen, dass beim Erstellen jeder Buchung auch die Buchung zur History hinzugefügt wird

    public Guest(long id, String firstName, String lastName, String title, LocalDate birthday, String eMail, String phoneNumber) {
        super(firstName, lastName, title, birthday);  // Passing parameters to the Person constructor

        // Initialize fields for the Guest class
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
}

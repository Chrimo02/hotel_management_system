package de.thws.fiw.backendsystems.templates.jpatemplate.domain.service;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.Guest;

import java.awt.print.Book;
import java.util.List;

public class GuestService {

    public void updateContactData(Guest guest, String newMail, String newPhone){
        guest.seteMail(newMail);
        guest.setPhoneNumber(newPhone);
    }
    public void changeEMail(Guest guest, String newMail){
        guest.seteMail(newMail);
    }
    public void changePhone(Guest guest, String newPhone){
        guest.setPhoneNumber(newPhone);
    }
    public List<Booking> getAllBookingsFromGuest(Guest guest){
        return guest.getBookingsHistory();
    }
}

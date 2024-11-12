package de.thws.fiw.backendsystems.templates.jpatemplate.domain.service;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.HotelRating;

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
    public void rateBooking(Booking booking, String comment, int starRating) throws RuntimeException{
        if (starRating < 1 || starRating > 5) throw new IllegalArgumentException("Invalid rating");
        HotelRating rating = HotelRating.values()[starRating - 1];
        if(comment != null) rating.setCommentRating(comment);
        booking.setRating(rating);
    }
}

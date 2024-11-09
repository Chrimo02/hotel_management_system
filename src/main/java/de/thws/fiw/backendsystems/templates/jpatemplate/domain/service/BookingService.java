package de.thws.fiw.backendsystems.templates.jpatemplate.domain.service;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.HotelRating;

public class BookingService {


    private void cancelBooking(Booking b){
        //TODO: Genauere Bedingungen für Stornierung noch implementieren
         b.setStatus(false);
    }
    public boolean isActive(Booking b){
        return b.getStatus();
    }

    public void rateBooking(Booking booking, String comment, int starRating) throws RuntimeException{
        if (starRating < 1 || starRating > 5) throw new IllegalArgumentException("Invalid rating");
        HotelRating rating = HotelRating.values()[starRating - 1];
        if(comment != null) rating.setCommentRating(comment);
        booking.setRating(rating);
    }
}

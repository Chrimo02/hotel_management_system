package de.thws.fiw.backendsystems.templates.jpatemplate.domain.service;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.model.Booking;

public class BookingService {


    private void cancelBooking(Booking b){
        //TODO: Genauere Bedingungen für Stornierung noch implementieren
         b.setStatus(false);
    }
}

package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

public class Hotel {

    long id;
    String name;
    String description;
    HotelLocation location;
    HotelRating rating; // -> in welcher Datenstruktur wollen wir Ratings darstellen?
    Room room;
}

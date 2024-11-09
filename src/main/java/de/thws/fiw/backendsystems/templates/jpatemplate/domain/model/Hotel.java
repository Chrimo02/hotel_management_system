package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

import java.util.List;

public class Hotel {

    private long id;
    private String name;
    private String description;
    private HotelLocation location;
    List<HotelRating> rating; // -> in welcher Datenstruktur wollen wir Ratings darstellen?
    List<Room> room;
}

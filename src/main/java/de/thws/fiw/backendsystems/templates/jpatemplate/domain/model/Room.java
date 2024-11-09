package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

public abstract class Room {

    //einzelne Room-types mit Vererbung implementieren
    long id;
    RoomIdentifier roomIdentifier;
    double pricePerNight;
    boolean available;


}

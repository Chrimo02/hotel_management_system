package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

import java.util.Date;

public class Booking {
    long id;
    Date checkInDate;
    Date checkOutDate;
    double totalPrice;
    Room room;
    Guest guest;

    //wie status implementieren?
}

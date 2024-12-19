package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;

import java.util.List;


public interface GuestRepository {
    void createGuest(Guest guest);

    void updateEmail(Guest guest);

    void updatePhone(Guest guest);

    void updateLastName(Guest guest);

    void updateTitle(Guest guest);

    void deleteGuest(long guestId);

    Guest getGuestById(long id);

    public List<Booking> getBookingsByGuestId(long guestId);
}

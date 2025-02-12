package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;

import java.util.List;


public interface GuestRepository {
    Guest createGuest(Guest guest);

    Guest updateGuest(Guest guest);

    void deleteGuest(Guest guest);

    Guest getGuestById(long id);

    public List<Booking> getBookingsByGuestId(long guestId);
}

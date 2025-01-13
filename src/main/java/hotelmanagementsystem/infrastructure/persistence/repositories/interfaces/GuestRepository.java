package hotelmanagementsystem.infrastructure.persistence.repositories.interfaces;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;

import java.util.List;


public interface GuestRepository {
    void createGuest(Guest guest);

    void updateEmail(Guest guest);

    void updatePhone(Guest guest);

    void updateLastName(Guest guest);

    void updateTitle(Guest guest);

    void deleteGuest(Guest guest);

    Guest getGuestById(long id);

    public List<Booking> getBookingsByGuestId(long guestId);
}

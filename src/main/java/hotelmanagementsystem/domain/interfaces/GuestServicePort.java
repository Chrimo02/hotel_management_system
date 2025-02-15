package hotelmanagementsystem.domain.interfaces;

import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;

import java.util.List;

public interface GuestServicePort {
     List<Booking> getAllBookingsFromGuest(long guestId);
     Guest createGuest(String firstName, String lastName, int year, int month, int day, String eMail, String phoneNumber);
     Guest updateEMail(long guestId, String newMail) throws GuestNotFoundException;
     Guest updatePhone(long guestId, String newPhone) throws GuestNotFoundException;
     Guest updateLastName(long guestId, String newLastName) throws GuestNotFoundException;
     void deleteGuest(long guestId) throws GuestNotFoundException;
     Guest getGuestById(long guestId) throws GuestNotFoundException;
     List<Guest> loadGuests(List<Long> guestIds);

}

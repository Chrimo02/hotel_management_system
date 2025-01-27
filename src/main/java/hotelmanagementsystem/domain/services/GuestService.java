package hotelmanagementsystem.domain.services;

import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.GuestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class GuestService {
    private final GuestRepository guestRepository;
    private final HotelService hotelService;

    @Inject
    public GuestService(GuestRepository guestRepository, HotelService hotelService){
        this.guestRepository = guestRepository;
        this.hotelService = hotelService;
    }
    public Guest createGuest(String firstName, String lastName, String title, int year,int month, int day, String eMail, String phoneNumber){
        Guest guest = new Guest.GuestBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withTitle(title)
                .withBirthday(year,month,day)
                .withEMail(eMail)
                .withPhoneNumber(phoneNumber)
                .build();
        guestRepository.createGuest(guest);
        return guest;
    }

    private Guest getNotNullGuest(long guestId) throws GuestNotFoundException {
        Guest guest = guestRepository.getGuestById(guestId);
        if (guest == null) throw new GuestNotFoundException("There is no Guest with the specified ID!");
        else return guest;
    }
    public List<Booking> getAllBookingsFromGuest(long guestId){
        return guestRepository.getBookingsByGuestId(guestId);
    }

    public Guest updateEMail(long guestId, String newMail) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.seteMail(newMail); //führt Änderung in der Business-logic aus
        guestRepository.updateEmail(guest); //callt den GuestDatabaseAdapter, damit die Änderung auch in der Datenbank gespeichert wird
        return guest;
    }
    public Guest updatePhone(long guestId, String newPhone) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.setPhoneNumber(newPhone);
        guestRepository.updatePhone(guest);
        return guest;
    }
    public Guest updateLastName(long guestId, String newLastName) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.setLastName(newLastName);
        guestRepository.updateLastName(guest);
        return guest;
    }
    public Guest updateTitle(long guestId, String newTitle) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.setTitle(newTitle);
        guestRepository.updateTitle(guest);
        return guest;
    }
    public void deleteGuest(long guestId) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guestRepository.deleteGuest(guest);
    }
    public Guest getGuestById(long guestId) throws GuestNotFoundException {
        return getNotNullGuest(guestId);
    }
    public List<Guest> loadGuests(List<Long> guestIds) {
        List<Guest> guests = new ArrayList<>();
        for (long guestId : guestIds) {
            try {
                Guest guest = getNotNullGuest(guestId);
                guests.add(guest);
            } catch (GuestNotFoundException e) {
                throw new RuntimeException("Guest with Id " + guestId + " not found");
            }
        }
        return guests;
    }
}

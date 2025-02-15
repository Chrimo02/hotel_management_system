package hotelmanagementsystem.domain.services;

import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.interfaces.GuestServicePort;
import hotelmanagementsystem.domain.interfaces.HotelServicePort;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.GuestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class GuestService implements GuestServicePort {
    private final GuestRepository guestRepository;

    @Inject
    public GuestService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }
    public Guest createGuest(String firstName, String lastName, int year,int month, int day, String eMail, String phoneNumber){
        Guest guest = new Guest.GuestBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withBirthday(year,month,day)
                .withEMail(eMail)
                .withPhoneNumber(phoneNumber)
                .build();
        return guestRepository.createGuest(guest);
    }

    private Guest getNotNullGuest(long guestId) throws GuestNotFoundException {
        Guest guest = guestRepository.getGuestById(guestId);
        if (guest == null) throw new GuestNotFoundException("There is no Guest with the specified ID!");
        else return guest;
    }
    @Override
    public List<Booking> getAllBookingsFromGuest(long guestId){
        return guestRepository.getBookingsByGuestId(guestId);
    }
    @Override
    public Guest updateEMail(long guestId, String newMail) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.seteMail(newMail);
        return guestRepository.updateGuest(guest);
    }
    @Override
    public Guest updatePhone(long guestId, String newPhone) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.setPhoneNumber(newPhone);
        return guestRepository.updateGuest(guest);
    }
    @Override
    public Guest updateLastName(long guestId, String newLastName) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.setLastName(newLastName);
        return guestRepository.updateGuest(guest);
    }
    @Override
    public void deleteGuest(long guestId) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guestRepository.deleteGuest(guest);
    }
    @Override
    public Guest getGuestById(long guestId) throws GuestNotFoundException {
        return getNotNullGuest(guestId);
    }
    @Override
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

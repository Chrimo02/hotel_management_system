package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.GuestNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.GuestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class GuestService {
    private final GuestRepository guestRepository;

    @Inject
    public GuestService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }
    public void createGuest(String firstName, String lastName, String title, int year,int month, int day, String eMail, String phoneNumber){
        Guest guest = new Guest.GuestBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withTitle(title)
                .withBirthday(year,month,day)
                .withEMail(eMail)
                .withPhoneNumber(phoneNumber)
                .build();
        guestRepository.createGuest(guest);
    }

    private Guest getNotNullGuest(long guestId) throws GuestNotFoundException{
        Guest guest = guestRepository.getGuestById(guestId);
        if (guest == null) throw new GuestNotFoundException("There is no Guest with the specified ID!");
        else return guest;
    }
    public List<Booking> getAllBookingsFromGuest(long guestId) throws GuestNotFoundException{
        Guest guest = getNotNullGuest(guestId);
        return guest.getBookingsHistory();
    }

    public void updateEMail(long guestId, String newMail) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.seteMail(newMail); //führt Änderung in der Business-logic aus
        guestRepository.updateEmail(guestId,newMail); //callt den GuestDatabaseAdapter, damit die Änderung auch in der Datenbank gespeichert wird
    }
    public void updatePhone(long guestId, String newPhone) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.setPhoneNumber(newPhone);
        guestRepository.updatePhone(guestId,newPhone);
    }
    public void updateLastName(long guestId, String newLastName) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.setLastName(newLastName);
        guestRepository.updateLastName(guestId,newLastName);
    }
    public void updateTitle(long guestId, String newTitle) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guest.setTitle(newTitle);
        guestRepository.updateTitle(guestId,newTitle);

    }
    public void deleteGuest(long guestId) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guestRepository.deleteGuest(guestId);
    }
    public Guest getGuestById(long guestId) throws GuestNotFoundException {
        return getNotNullGuest(guestId);
    }
}

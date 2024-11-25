package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.GuestNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRating;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.GuestRepository;

import java.time.LocalDate;
import java.util.List;

public class GuestService {
    private final GuestRepository guestRepository;
    public GuestService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }
    void createGuest(String firstName, String lastName, String title, int yearBirthday,int monthBirtday, int dayBirthday, String eMail, String phoneNumber){
        guestRepository.createGuest(firstName,lastName,title,yearBirthday,monthBirtday,dayBirthday,eMail,phoneNumber);
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
    void deleteGuest(long guestId) throws GuestNotFoundException {
        Guest guest = getNotNullGuest(guestId);
        guestRepository.deleteGuest(guestId);
    }
    private Guest getGuestById(long guestId) throws GuestNotFoundException {
        return getNotNullGuest(guestId);
    }
}

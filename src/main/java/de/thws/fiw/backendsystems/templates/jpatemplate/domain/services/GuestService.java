package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.GuestNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRating;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.GuestRepository;

import java.time.LocalDate;
import java.util.List;

public class GuestService {
    //TODO: Code repetition vermeiden, einheitliche Methode für check ob guest null
    private final GuestRepository guestRepository;
    public GuestService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }
    void createGuest(String firstName, String lastName, String title, int yearBirthday,int monthBirtday, int dayBirthday, String eMail, String phoneNumber){
        guestRepository.createGuest(firstName,lastName,title,yearBirthday,monthBirtday,dayBirthday,eMail,phoneNumber);
    }
    public List<Booking> getAllBookingsFromGuest(long guestId) throws GuestNotFoundException{
        Guest guest = guestRepository.getGuestById(guestId);
        if (guest != null){
            return guest.getBookingsHistory();
        }
        else throw new GuestNotFoundException("There is no Guest with the specified id!");
    }

    public void updateEMail(long guestId, String newMail) throws GuestNotFoundException {
        Guest guest = guestRepository.getGuestById(guestId);
        if (guest != null) {
            guest.seteMail(newMail); //führt Änderung in der Business-logic aus
            guestRepository.updateEmail(guestId,newMail); //callt den GuestDatabaseAdapter, damit die Änderung auch in der Datenbank gespeichert wird

        }
        else throw new GuestNotFoundException("There is no Guest with the specified id!");
    }
    public void updatePhone(long guestId, String newPhone) throws GuestNotFoundException {
        Guest guest = guestRepository.getGuestById(guestId);
        if (guest != null){
            guest.setPhoneNumber(newPhone);
            guestRepository.updateEmail(guestId,newPhone);
        }
        else throw new GuestNotFoundException("There is no Guest with the specified id!");
    }
    public void updateLastName(long guestId, String newLastName) throws GuestNotFoundException {
        Guest guest = guestRepository.getGuestById(guestId);
        if (guest != null){
            guest.setLastName(newLastName);
            guestRepository.updateLastName(guestId,newLastName);
        }
        else throw new GuestNotFoundException("There is no Guest with the specified id!");

    }
    public void updateTitle(long guestId, String newTitle) throws GuestNotFoundException {
        Guest guest = guestRepository.getGuestById(guestId);
        if (guest != null){
            guest.setTitle(newTitle);
            guestRepository.updateTitle(guestId,newTitle);
        }
        else throw new GuestNotFoundException("There is no Guest with the specified id!");
    }
    void deleteGuest(long guestId) throws GuestNotFoundException {
        Guest guest = guestRepository.getGuestById(guestId);
        if (guest != null){
            guestRepository.deleteGuest(guestId);
        }
        else throw new GuestNotFoundException("There is no Guest with the specified id!");
    }
    private Guest getGuestById(long id) throws GuestNotFoundException {
        Guest guest = guestRepository.getGuestById(id);
        if (guest != null) return guest;
        else throw new GuestNotFoundException("There is no Guest with the specified id!");
    }
}

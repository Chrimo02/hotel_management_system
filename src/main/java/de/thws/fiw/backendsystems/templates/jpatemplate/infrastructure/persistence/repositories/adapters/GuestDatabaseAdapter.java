package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.GuestDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.GuestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.stream.Collectors;

@ApplicationScoped
public class GuestDatabaseAdapter implements GuestRepository {
    private final GuestDAO guestDAO;
    @Inject
    public GuestDatabaseAdapter(GuestDAO guestDAO){
        this.guestDAO = guestDAO;
    }

    @Override
    public void createGuest(String firstName, String lastName, String title, int yearBirthday, int monthBirthday, int dayBirthday, String eMail, String phoneNumber) {
        GuestEntity guestEntity = new GuestEntity(firstName,lastName,title,yearBirthday,monthBirthday,dayBirthday,eMail,phoneNumber);
        guestDAO.create(guestEntity);
    }

    @Override
    public void updateEmail(long guestId, String newMail) {
        GuestEntity guestEntity = guestDAO.read(guestId);
        guestEntity.seteMail(newMail);
        guestDAO.update(guestEntity);
    }

    @Override
    public void updatePhone(long guestId, String newPhone) {
        GuestEntity guestEntity = guestDAO.read(guestId);
        guestEntity.setPhoneNumber(newPhone);
        guestDAO.update(guestEntity);
    }

    @Override
    public void updateLastName(long guestId, String newLastName) {
        GuestEntity guestEntity = guestDAO.read(guestId);
        guestEntity.setLastName(newLastName);
        guestDAO.update(guestEntity);
    }

    @Override
    public void updateTitle(long guestId, String newTitle) {
        GuestEntity guestEntity = guestDAO.read(guestId);
        guestEntity.setTitle(newTitle);
        guestDAO.update(guestEntity);
    }

    @Override
    public void deleteGuest(long guestId) {
        GuestEntity guestEntity = guestDAO.read(guestId);
        guestDAO.delete(guestEntity);
    }

    @Override
    public Guest getGuestById(long id) {
        GuestEntity guest = guestDAO.read(id);
        Guest newGuest = new Guest.GuestBuilder()
                .withFirstName(guest.getFirstName())
                .withLastName(guest.getLastName())
                .withTitle(guest.getTitle())
                .withBirthday(guest.getBirthday().getYear(),guest.getBirthday().getMonthValue(),guest.getBirthday().getDayOfMonth())
                .withEMail(guest.geteMail())
                .withPhoneNumber(guest.getPhoneNumber())
                .build();
        newGuest.setBookingsHistory(guest.getBookingsHistory()); //TODO: Konvertierungs-Methoden für Entity zu Domain-Objekten implementieren
        return newGuest;
    }
}

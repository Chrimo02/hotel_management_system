package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.GuestDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.BookingMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.GuestMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.GuestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GuestDatabaseAdapter implements GuestRepository {
    private final GuestDAO guestDAO;
    private final BookingMapper bookingMapper;
    private final GuestMapper guestMapper;
    @Inject
    public GuestDatabaseAdapter(GuestDAO guestDAO, BookingMapper bookingMapper, GuestMapper guestMapper){
        this.guestDAO = guestDAO;
        this.bookingMapper = bookingMapper;
        this.guestMapper = guestMapper;
    }

    @Override
    public void createGuest(Guest guest) {
        GuestEntity guestEntity = guestMapper.guestToGuestEntity(guest);
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
        GuestEntity entity = guestDAO.read(id);
        Guest newGuest = new Guest.GuestBuilder()
                .withFirstName(entity.getFirstName())
                .withLastName(entity.getLastName())
                .withTitle(entity.getTitle())
                .withBirthday(entity.getBirthday().getYear(), entity.getBirthday().getMonthValue(), entity.getBirthday().getDayOfMonth())
                .withEMail(entity.geteMail())
                .withPhoneNumber(entity.getPhoneNumber())
                .build();
        List<Booking> history = bookingMapper.toDomainList(entity.getBookingsHistory());
        newGuest.setBookingsHistory(history);
        return newGuest;
    }
}

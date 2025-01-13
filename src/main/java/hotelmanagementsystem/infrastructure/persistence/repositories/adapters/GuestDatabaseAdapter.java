package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.GuestDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.GuestMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.GuestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import java.util.List;
import java.util.stream.Collectors;

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
    public void updateEmail(Guest guest) {
        GuestEntity guestEntity = guestDAO.read(guest.getId());
        guestEntity.seteMail(guest.geteMail());
        guestDAO.update(guestEntity);
    }

    @Override
    public void updatePhone(Guest guest) {
        GuestEntity guestEntity = guestDAO.read(guest.getId());
        guestEntity.setPhoneNumber(guest.getPhoneNumber());
        guestDAO.update(guestEntity);
    }

    @Override
    public void updateLastName(Guest guest) {
        GuestEntity guestEntity = guestDAO.read(guest.getId());
        guestEntity.setLastName(guest.getLastName());
        guestDAO.update(guestEntity);
    }

    @Override
    public void updateTitle(Guest guest) {
        GuestEntity guestEntity = guestDAO.read(guest.getId());
        guestEntity.setTitle(guest.getTitle());
        guestDAO.update(guestEntity);
    }

    @Override
    public void deleteGuest(Guest guest) {
        GuestEntity guestEntity = guestMapper.guestToGuestEntity(guest);
        guestDAO.delete(guestEntity);
    }

    @Override
    public Guest getGuestById(long id) {
        GuestEntity entity = guestDAO.read(id);
        return new Guest.GuestBuilder()
                .withId(entity.getId())
                .withFirstName(entity.getFirstName())
                .withLastName(entity.getLastName())
                .withTitle(entity.getTitle())
                .withBirthday(entity.getBirthday().getYear(), entity.getBirthday().getMonthValue(), entity.getBirthday().getDayOfMonth())
                .withEMail(entity.geteMail())
                .withPhoneNumber(entity.getPhoneNumber())
                .build();
    }

    public List<Booking> getBookingsByGuestId(long guestId) {
        List<BookingEntity> bookingEntities = guestDAO.findBookingsByGuestId(guestId);
        return bookingEntities.stream()
                .map(bookingMapper::bookingEntityToBooking)
                .collect(Collectors.toList());
    }
}

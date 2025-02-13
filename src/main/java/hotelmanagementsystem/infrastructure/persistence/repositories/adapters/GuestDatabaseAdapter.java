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
    public Guest createGuest(Guest guest) {
        GuestEntity guestEntity = guestMapper.guestToGuestEntity(guest);
        GuestEntity createdGuest = guestDAO.create(guestEntity);
        return guestMapper.guestEntityToGuest(createdGuest);
    }

    @Override
    public Guest updateGuest(Guest guest) {
        GuestEntity guestEntity = guestDAO.read(guest.getId());
        guestEntity.seteMail(guest.geteMail());
        guestEntity.setPhoneNumber(guest.getPhoneNumber());
        guestEntity.setLastName(guest.getLastName());
        return guestMapper.guestEntityToGuest(guestDAO.update(guestEntity));

    }

    @Override
    public void deleteGuest(Guest guest) {
        GuestEntity guestEntity = guestMapper.guestToGuestEntity(guest);
        guestDAO.delete(guestEntity);
    }

    @Override
    public Guest getGuestById(long id) {
        GuestEntity entity = guestDAO.read(id);
        return guestMapper.guestEntityToGuest(entity);

    }

    public List<Booking> getBookingsByGuestId(long guestId) {
        List<BookingEntity> bookingEntities = guestDAO.findBookingsByGuestId(guestId);
        return bookingEntities.stream()
                .map(bookingMapper::bookingEntityToBooking)
                .collect(Collectors.toList());
    }
}

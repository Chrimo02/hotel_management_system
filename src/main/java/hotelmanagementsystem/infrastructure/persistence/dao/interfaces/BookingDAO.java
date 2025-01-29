package hotelmanagementsystem.infrastructure.persistence.dao.interfaces;

import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import jakarta.persistence.EntityManager;

public interface BookingDAO {
    BookingEntity create(BookingEntity bookingEntity);
    BookingEntity read(long id);
    void update(BookingEntity guestEntity);
    //no need to delete bookings because we have cancel method and so it makes bookinghistory possible
}


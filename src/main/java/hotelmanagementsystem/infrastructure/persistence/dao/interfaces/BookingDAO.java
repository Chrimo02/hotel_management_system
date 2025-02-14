package hotelmanagementsystem.infrastructure.persistence.dao.interfaces;

import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingDAO {

    List<BookingEntity> findBookingsByCheckInDate(LocalDate checkInDate);

    BookingEntity create(BookingEntity bookingEntity);
    BookingEntity read(long id);
    void update(BookingEntity guestEntity);
}


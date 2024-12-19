package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface BookingDAO {
    EntityManager entityManager();
    BookingEntity create(BookingEntity bookingEntity);
    BookingEntity read(long id);
    void update(BookingEntity guestEntity);
    //no need to delete bookings because we have cancel method and so it makes bookinghistory possible
}


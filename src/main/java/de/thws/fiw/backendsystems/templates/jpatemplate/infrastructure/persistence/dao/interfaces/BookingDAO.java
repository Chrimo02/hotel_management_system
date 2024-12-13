package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;
import jakarta.persistence.EntityManager;

public interface BookingDAO {
    EntityManager entityManager();
    void create(BookingEntity bookingEntity);
    BookingEntity read(long id);
    void update(BookingEntity guestEntity);
    //no need to delete bookings because we have cancel method and so it makes bookinghistory possible
}


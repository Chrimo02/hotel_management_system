package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import jakarta.persistence.EntityManager;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;

import java.util.List;

public interface GuestDAO {
    EntityManager entityManager();
    void create(GuestEntity guestEntity);
    GuestEntity read(long id);
    void update(GuestEntity guestEntity);
    void delete(GuestEntity guestEntity);

    List<BookingEntity> findBookingsByGuestId(long guestId);

}

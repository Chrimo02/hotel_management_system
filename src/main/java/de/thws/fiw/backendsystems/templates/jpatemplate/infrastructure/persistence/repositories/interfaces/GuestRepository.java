package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;


public interface GuestRepository {
    void createGuest(Guest guest);
    void updateEmail(long guestId, String newEmail);
    void updatePhone(long guestId, String newPhone);
    void updateLastName(long guestId, String newLastName);
    void updateTitle(long guestId, String newTitle);
    void deleteGuest(long guestId);
    Guest getGuestById(long id);
}

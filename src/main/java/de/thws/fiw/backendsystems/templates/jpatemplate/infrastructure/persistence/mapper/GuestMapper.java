package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GuestMapper {
    private final BookingMapper bookingMapper;
    @Inject
    public GuestMapper(BookingMapper bookingMapper){
        this.bookingMapper = bookingMapper;
    }
    public GuestEntity guestToGuestEntity(Guest guest){
        GuestEntity guestEntity = new GuestEntity(guest.getFirstName(), guest.getLastName(), guest.getTitle(),guest.getBirthday().getYear(),guest.getBirthday().getMonthValue(),guest.getBirthday().getDayOfMonth(),guest.geteMail(),guest.getPhoneNumber());
        List<BookingEntity> history = guest.getBookingsHistory().stream().map(bookingMapper::bookingToBookingEntity).toList();
        guestEntity.setBookingsHistory(history);
        guestEntity.setId(guest.getId());
        return guestEntity;
    }

    public List<GuestEntity> guestsToGuestEntities(List<Guest> guests){
        if(guests == null) return null;

        List<GuestEntity> result = new ArrayList<>();
        for (Guest g : guests){
            GuestEntity entity = this.guestToGuestEntity(g);
            result.add(entity);
        }
        return result;
    }
    public Guest guestEntityToGuest(GuestEntity guestEntity){
        Guest guest = new Guest.GuestBuilder()
                .withFirstName(guestEntity.getFirstName())
                .withLastName(guestEntity.getLastName())
                .withTitle(guestEntity.getTitle())
                .withBirthday(guestEntity.getBirthday().getYear(),guestEntity.getBirthday().getMonthValue(),guestEntity.getBirthday().getDayOfMonth())
                .withEMail(guestEntity.geteMail())
                .withPhoneNumber(guestEntity.getPhoneNumber())
                .build();
        List<Booking> history = guestEntity.getBookingsHistory().stream().map(bookingMapper::bookingEntityToBooking).toList();
        guest.setBookingsHistory(history);
        guest.setId(guestEntity.getId());
        return guest;
    }
    public List<Guest> guestEntitiesToGuests(List<GuestEntity> guestEntities){
        if(guestEntities == null) return null;
        List<Guest> result = new ArrayList<>();
        for (GuestEntity ge : guestEntities){
            Guest guest = this.guestEntityToGuest(ge);
            result.add(guest);
        }
        return result;
    }
}

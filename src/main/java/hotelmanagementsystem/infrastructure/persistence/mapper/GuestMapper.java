package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GuestMapper {
    @Inject
    public GuestMapper(){
    }
    public GuestEntity guestToGuestEntity(Guest guest){
        GuestEntity guestEntity = new GuestEntity(guest.getFirstName(), guest.getLastName(),guest.getBirthday().getYear(),guest.getBirthday().getMonthValue(),guest.getBirthday().getDayOfMonth(),guest.geteMail(),guest.getPhoneNumber());
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
        if (guestEntity == null) {
            return null;
        }
        Guest guest = new Guest.GuestBuilder()
                .withId(guestEntity.getId())
                .withFirstName(guestEntity.getFirstName())
                .withLastName(guestEntity.getLastName())
                .withBirthday(guestEntity.getBirthday().getYear(),guestEntity.getBirthday().getMonthValue(),guestEntity.getBirthday().getDayOfMonth())
                .withEMail(guestEntity.geteMail())
                .withPhoneNumber(guestEntity.getPhoneNumber())
                .build();
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

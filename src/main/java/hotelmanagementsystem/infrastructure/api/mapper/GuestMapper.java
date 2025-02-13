package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.api.dto.GuestDTO;

public class GuestMapper {
    public static GuestDTO toDTO(Guest guest){
        GuestDTO dto = new GuestDTO();
        dto.setId(guest.getId());
        dto.setFirstName(guest.getFirstName());
        dto.setLastName(guest.getLastName());
        dto.setBirthday(guest.getBirthday());
        dto.seteMail(guest.geteMail());
        dto.setPhoneNumber(guest.getPhoneNumber());
        return dto;
    }

    public static Guest toDomain(GuestDTO dto){
        Guest guest = new Guest.GuestBuilder()
                .withId(dto.getId())
                .withFirstName(dto.getFirstName())
                .withLastName(dto.getLastName())
                .withBirthday(dto.getBirthday().getYear(),dto.getBirthday().getMonthValue(),dto.getBirthday().getDayOfMonth())
                .withEMail(dto.geteMail())
                .withPhoneNumber(dto.getPhoneNumber())
                .build();
        return guest;
    }
}

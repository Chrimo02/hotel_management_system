package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {

    // --- Map Domain Booking -> DTO ---
    public static BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setHotelId(booking.getHotel().getId());

        // Alle Gast-IDs extrahieren
        List<Long> guestIds = booking.getGuests().stream()
                .map(Guest::getId)
                .collect(Collectors.toList());
        dto.setGuestIds(guestIds);

        // Alle Raum-IDs extrahieren
        List<Long> roomIds = booking.getRooms().stream()
                .map(Room::getId)
                .collect(Collectors.toList());
        dto.setRoomIds(roomIds);

        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setStatus(booking.getStatus());
        dto.setTotalPrice(booking.getTotalPrice());

        // Optional: Check-In und Check-Out Zeiten setzen
        if (booking.getCheckInTime() != null) {
            dto.setCheckInTime(booking.getCheckInTime());
        }

        if (booking.getCheckOutTime() != null) {
            dto.setCheckOutTime(booking.getCheckOutTime());
        }

        return dto;
    }

    // --- Map DTO -> Domain Booking ---
    public static Booking toDomain(BookingDTO dto, Hotel hotel, List<Room> rooms, List<Guest> guests) {
        return new Booking(
                dto.getId(),
                hotel,
                dto.getCheckInDate(),
                dto.getCheckOutDate(),
                rooms,
                guests,
                true,   // Standardstatus ist aktiv
                dto.getCheckInTime(),
                dto.getCheckOutTime()
        );
    }
}

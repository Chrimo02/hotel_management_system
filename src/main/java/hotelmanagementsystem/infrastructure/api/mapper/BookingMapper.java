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

        // Extract all guest IDs
        List<Long> guestIds = booking.getGuests().stream()
                .map(Guest::getId)
                .collect(Collectors.toList());
        dto.setGuestIds(guestIds);

        // Extract all room IDs
        List<Long> roomIds = booking.getRooms().stream()
                .map(Room::getId)
                .collect(Collectors.toList());
        dto.setRoomIds(roomIds);

        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());

        return dto;
    }

    // --- Map DTO -> Domain Booking ---
    // Typically used if you're reconstructing a domain Booking from a DTO, e.g. if you had an update scenario.
    // For createBooking, you generally pass domain data to BookingService directly (like room classes, etc.)
    public static Booking toDomain(BookingDTO dto, Hotel hotel, List<Room> rooms, List<Guest> guests) {
        return new Booking(
                dto.getId(),
                hotel,
                dto.getCheckInDate(),
                dto.getCheckOutDate(),
                rooms,
                guests,
                true,   // default status
                null,   // check-in time
                null    // check-out time
        );
    }
}

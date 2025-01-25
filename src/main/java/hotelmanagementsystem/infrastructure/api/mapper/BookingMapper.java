package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {

    // Map Domain Booking to DTO
    public static BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setHotelId(booking.getHotel().getId());
        dto.setGuestId(booking.getGuests().get(0).getId()); // Assuming one primary guest
        dto.setRoomIds(booking.getRooms().stream().map(Room::getId).collect(Collectors.toList()));
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        return dto;
    }

    // Map DTO to Domain Booking
    public static Booking toDomain(BookingDTO dto, Hotel hotel, List<Room> rooms, List<Guest> guests) {
        return new Booking(
                dto.getId(),
                hotel,
                dto.getCheckInDate(),
                dto.getCheckOutDate(),
                rooms,
                guests,
                true, // Default status is active
                null, // Check-in time not set initially
                null  // Check-out time not set initially
        );
    }
}

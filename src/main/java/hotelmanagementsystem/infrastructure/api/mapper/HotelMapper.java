package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.api.dto.HotelDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HotelMapper {

    // --- Domain -> DTO ---
    public static HotelDTO toDTO(Hotel hotel) {
        if (hotel == null) {
            return null;
        }
        HotelDTO dto = new HotelDTO();
        // Falls 'hotel.getId()' null sein kann, als Fallback 0 setzen
        dto.setId(hotel.getId() != null ? hotel.getId() : 0L);
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setAverageRating(hotel.getAverageRating());

        // Rooms -> roomIds
        if (hotel.getRooms() != null) {
            List<Long> roomIds = hotel.getRooms().stream()
                    .map(Room::getId)
                    .collect(Collectors.toList());
            dto.setRoomIds(roomIds);
        } else {
            dto.setRoomIds(Collections.emptyList());
        }

        // Bookings -> bookingIds
        if (hotel.getBookings() != null) {
            List<Long> bookingIds = hotel.getBookings().stream()
                    .map(Booking::getId)
                    .collect(Collectors.toList());
            dto.setBookingIds(bookingIds);
        } else {
            dto.setBookingIds(Collections.emptyList());
        }

        return dto;
    }


    public static Hotel toDomain(HotelDTO dto,
                                 List<Room> rooms,
                                 List<Booking> bookings) {
        if (dto == null) {
            return null;
        }

        // Hotel per Builder erstellen
        Hotel hotel = new Hotel.HotelBuilder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withDescription(dto.getDescription())
                .withRoomsList(rooms)
                .withBookingList(bookings)
                // Hier ggf. .withLocation(...) oder .withRatingMap(...) ergänzen,
                // falls man das ebenfalls abbilden möchte
                .build();

        // Den AverageRating übernehmen – falls nicht „on the fly“ berechnet
        hotel.setAverageRating(dto.getAverageRating());

        return hotel;
    }
}

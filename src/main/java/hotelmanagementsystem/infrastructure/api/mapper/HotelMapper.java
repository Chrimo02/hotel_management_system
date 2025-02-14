package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.api.dto.HotelDTO;
import hotelmanagementsystem.infrastructure.api.dto.HotelRatingDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HotelMapper {

    public static HotelDTO toDTO(Hotel hotel) {
        if (hotel == null) {
            return null;
        }
        HotelDTO dto = new HotelDTO();
        dto.setId(hotel.getId() != null ? hotel.getId() : 0L);
        dto.setName(hotel.getName());
        dto.setDescription(hotel.getDescription());
        dto.setAverageRating(hotel.getAverageRating());
        dto.setHotelLocation(HotelLocationMapper.toDTO(hotel.getLocation()));

        if (hotel.getRooms() != null) {
            List<Long> roomIds = hotel.getRooms().stream()
                    .map(Room::getId)
                    .collect(Collectors.toList());
            dto.setRoomIds(roomIds);
        } else {
            dto.setRoomIds(Collections.emptyList());
        }

        if (hotel.getBookings() != null) {
            List<Long> bookingIds = hotel.getBookings().stream()
                    .map(Booking::getId)
                    .collect(Collectors.toList());
            dto.setBookingIds(bookingIds);
        } else {
            dto.setBookingIds(Collections.emptyList());
        }

        if (hotel.getRatings() != null) {
            List<HotelRatingDTO> ratings = hotel.getRatings().stream()
                    .map(HotelRatingMapper::toDTO)
                    .toList();
            dto.setHotelRatings(ratings);
        } else {
            dto.setHotelRatings(Collections.emptyList());
        }
        return dto;
    }

    public static Hotel toDomain(HotelDTO dto,
                                 List<Room> rooms,
                                 List<Booking> bookings) {
        if (dto == null) {
            return null;
        }

        return new Hotel.HotelBuilder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withDescription(dto.getDescription())
                .withLocation(HotelLocationMapper.fromDTO(dto.getHotelLocation()))
                .withRoomsList(rooms)
                .withBookingList(bookings)
                .withAverageRating(dto.getAverageRating())
                .build();
    }
}

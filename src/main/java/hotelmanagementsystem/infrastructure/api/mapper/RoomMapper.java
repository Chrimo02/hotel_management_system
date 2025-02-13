package hotelmanagementsystem.infrastructure.api.mapper;

import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;
import hotelmanagementsystem.infrastructure.api.dto.RoomDTO;
import hotelmanagementsystem.infrastructure.api.dto.RoomIdentifierDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoomMapper {

 public static RoomDTO toDTO(Room room) {
  RoomIdentifierDTO roomIdentifierDTO = RoomIdentifierMapper.toDTO(room.getRoomIdentifier());
  List<BookingDTO> bookingDTOs = room.getBookings().stream()
          .map(BookingMapper::toDTO)
          .collect(Collectors.toList());

  return new RoomDTO(
          room.getId(),
          room.getPricePerNight(),
          roomIdentifierDTO,
          room.getHotel().getId(),
          bookingDTOs,
          room.getClass().getSimpleName()
  );
 }

 public static Room toDomain(RoomDTO dto, Hotel hotel, Set<Booking> bookings) {
  RoomIdentifier roomIdentifier = RoomIdentifierMapper.toDomain(dto.getRoomIdentifierDTO());

  Room room;
  if (dto.getType().equalsIgnoreCase("SingleRoom")) {
   room = new SingleRoom.Builder(dto.getPricePerNight(), roomIdentifier, hotel)
           .withId(dto.getId())
           .build();
  } else if (dto.getType().equalsIgnoreCase("DoubleRoom")) {
   room = new DoubleRoom.Builder(dto.getPricePerNight(), roomIdentifier, hotel)
           .withId(dto.getId())
           .build();
  } else {
   throw new IllegalArgumentException("Unsupported room type: " + dto.getType());
  }

  room.setBookings(bookings);
  return room;
 }
}

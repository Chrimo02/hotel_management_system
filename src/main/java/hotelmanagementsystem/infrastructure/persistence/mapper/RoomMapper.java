package hotelmanagementsystem.infrastructure.persistence.mapper;
import java.util.Set;
import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.infrastructure.persistence.entities.DoubleRoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.SingleRoomEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RoomMapper {

    private final RoomIdentifierMapper roomIdentifierMapper;
    private final BookingMapper bookingMapper;

    @Inject
    public RoomMapper(RoomIdentifierMapper roomIdentifierMapper, BookingMapper bookingMapper) {
        this.roomIdentifierMapper = roomIdentifierMapper;
        this.bookingMapper = bookingMapper;
    }

    public RoomEntity domainToEntity(Room room) {
        if (room == null) return null;

        RoomIdentifier roomIdentifierDomain = room.getRoomIdentifier();
        RoomIdentifierEntity riEntity = null;
        if (roomIdentifierDomain != null) {
            riEntity = roomIdentifierMapper.domainToEntity(roomIdentifierDomain);
        }

        HotelEntity minimalHotelEntity = null;
        Hotel domainHotel = room.getHotel();
        if (domainHotel != null && domainHotel.getId() != null) {
            minimalHotelEntity = new HotelEntity.HotelBuilder()
                    .withId(domainHotel.getId())
                    .build();
        }

        RoomEntity roomEntity;
        if (room instanceof SingleRoom) {
            roomEntity = new SingleRoomEntity(
                    room.getId(),
                    room.getPricePerNight(),
                    riEntity,
                    minimalHotelEntity
            );
        } else if (room instanceof DoubleRoom) {
            roomEntity = new DoubleRoomEntity(
                    room.getId(),
                    room.getPricePerNight(),
                    riEntity,
                    minimalHotelEntity
            );
        } else {
            throw new RuntimeException("Unsupported Room subtype: " + room.getClass().getSimpleName());
        }

        roomEntity.setRoomIdentifier(riEntity);

        return roomEntity;
    }

    public List<RoomEntity> toEntityList(List<Room> domainRooms) {
        if (domainRooms == null) return null;
        return domainRooms.stream()
                .map(this::domainToEntity)
                .collect(Collectors.toList());
    }

    public Room entityToDomain(RoomEntity roomEntity) {
        if (roomEntity == null) return null;

        RoomIdentifier roomIdentifier =
                roomIdentifierMapper.entityToDomain(roomEntity.getRoomIdentifier());

        Hotel hotelDomain = null;
        if (roomEntity.getHotel() != null) {
            hotelDomain = new Hotel.HotelBuilder()
                    .withId(roomEntity.getHotel().getId())
                    .withName(roomEntity.getHotel().getName())
                    .build();
        }

        Room domainRoom;
        if (roomEntity instanceof SingleRoomEntity) {
            domainRoom = new SingleRoom.Builder(
                    roomEntity.getPricePerNight(),
                    roomIdentifier,
                    hotelDomain
            ).withId(roomEntity.getId()).build();
        } else if (roomEntity instanceof DoubleRoomEntity) {
            domainRoom = new DoubleRoom.Builder(
                    roomEntity.getPricePerNight(),
                    roomIdentifier,
                    hotelDomain
            ).withId(roomEntity.getId()).build();
        } else {
            throw new RuntimeException("Unknown subtype of RoomEntity: " + roomEntity.getClass().getSimpleName());
        }

        Set<Booking> bookings = roomEntity.getBookings().stream()
                .map(bookingMapper::toMinimalBooking)
                .collect(Collectors.toSet());
        domainRoom.setBookings(bookings);

        return domainRoom;
    }


    public List<Room> toDomainList(List<RoomEntity> roomEntities) {
        if (roomEntities == null) return null;
        return roomEntities.stream()
                .map(this::entityToDomain)
                .collect(Collectors.toList());
    }

    public Room toMinimalDomain(RoomEntity roomEntity) {
        if (roomEntity == null) return null;
        RoomIdentifier roomIdentifier = roomIdentifierMapper.entityToDomain(roomEntity.getRoomIdentifier());
        Hotel minimalHotel = null;

        if (roomEntity instanceof SingleRoomEntity) {
            return new SingleRoom.Builder(
                    roomEntity.getPricePerNight(),
                    roomIdentifier,
                    minimalHotel
            ).withId(roomEntity.getId()).build();
        } else if (roomEntity instanceof DoubleRoomEntity) {
            return new DoubleRoom.Builder(
                    roomEntity.getPricePerNight(),
                    roomIdentifier,
                    minimalHotel
            ).withId(roomEntity.getId()).build();
        } else {
            throw new RuntimeException("Unsupported Room subtype: " + roomEntity.getClass().getSimpleName());
        }
    }



}

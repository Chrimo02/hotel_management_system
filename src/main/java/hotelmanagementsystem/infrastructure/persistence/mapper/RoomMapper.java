package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.models.SingleRoom;
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

    @Inject
    public RoomMapper(RoomIdentifierMapper roomIdentifierMapper) {
        this.roomIdentifierMapper = roomIdentifierMapper;
    }

    // ---------------------------------------
    // DOMAIN -> ENTITY
    // ---------------------------------------
    public RoomEntity domainToEntity(Room room) {
        if (room == null) return null;

        // RoomIdentifier -> Entity
        RoomIdentifierEntity riEntity =
                roomIdentifierMapper.domainToEntity(room.getRoomIdentifier());

        // Minimales HotelEntity (nur ID), um keine Schleife Hotel->Room->Hotel zu triggern
        HotelEntity minimalHotelEntity = null;
        Hotel domainHotel = room.getHotel();
        if (domainHotel != null && domainHotel.getId() != null) {
            minimalHotelEntity = new HotelEntity.HotelBuilder()
                    .withId(domainHotel.getId())
                    .build();
            // Optional: .withName(domainHotel.getName()), etc.
        }

        // RoomEntity anlegen (SingleRoomEntity oder DoubleRoomEntity)
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

        // **Wichtig**: OneToOne-Beziehung in beide Richtungen setzen
        roomEntity.setRoomIdentifier(riEntity);
        if (riEntity != null) {
            riEntity.setRoom(roomEntity);
        }

        // Optional: Buchungen hier nicht oder nur flach mappen, um endlose Schleifen zu vermeiden
        // roomEntity.setBookings(...);

        return roomEntity;
    }

    public List<RoomEntity> toEntityList(List<Room> domainRooms) {
        if (domainRooms == null) return null;
        return domainRooms.stream()
                .map(this::domainToEntity)
                .collect(Collectors.toList());
    }

    // ---------------------------------------
    // ENTITY -> DOMAIN
    // ---------------------------------------
    public Room entityToDomain(RoomEntity roomEntity) {
        if (roomEntity == null) return null;

        RoomIdentifier roomIdentifier =
                roomIdentifierMapper.entityToDomain(roomEntity.getRoomIdentifier());

        // Minimaler Hotel-Domain-Teil (nur ID/Name), um keine Schleife zu verursachen
        Hotel hotelDomain = null;
        if (roomEntity.getHotel() != null) {
            hotelDomain = new Hotel.HotelBuilder()
                    .withId(roomEntity.getHotel().getId())
                    .withName(roomEntity.getHotel().getName()) // optional
                    .build();
        }

        // SingleRoom oder DoubleRoom?
        Room domainRoom;
        if (roomEntity instanceof SingleRoomEntity) {
            domainRoom = new SingleRoom.Builder(
                    roomEntity.getPricePerNight(),
                    roomIdentifier,
                    hotelDomain
            )
                    .withId(roomEntity.getId())
                    .build();
        } else if (roomEntity instanceof DoubleRoomEntity) {
            domainRoom = new DoubleRoom.Builder(
                    roomEntity.getPricePerNight(),
                    roomIdentifier,
                    hotelDomain
            )
                    .withId(roomEntity.getId())
                    .build();
        } else {
            throw new RuntimeException("Unknown subtype of RoomEntity: "
                    + roomEntity.getClass().getSimpleName());
        }

        // Buchungen -> optional flach mappen (z. B. nur IDs) oder weglassen

        return domainRoom;
    }

    public List<Room> toDomainList(List<RoomEntity> roomEntities) {
        if (roomEntities == null) return null;
        return roomEntities.stream()
                .map(this::entityToDomain)
                .collect(Collectors.toList());
    }
}

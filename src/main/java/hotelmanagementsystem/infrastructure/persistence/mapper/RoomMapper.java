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
    private final BookingMapper bookingMapper;

    @Inject
    public RoomMapper(RoomIdentifierMapper roomIdentifierMapper, BookingMapper bookingMapper) {
        this.roomIdentifierMapper = roomIdentifierMapper;
        this.bookingMapper = bookingMapper;
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

        // Minimaler Hotel-Domain-Teil (nur ID und evtl. Name)
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

        // Hier verwenden wir vorhandene Daten, falls möglich.
        // Falls du wirklich nur die ID benötigst, kannst du auch Standardwerte oder null übergeben.
        RoomIdentifier roomIdentifier = roomIdentifierMapper.entityToDomain(roomEntity.getRoomIdentifier());

        // Falls du im Minimalmapping keine Hotel-Details brauchst, kannst du hier auch null übergeben.
        Hotel minimalHotel = null;

        if (roomEntity instanceof SingleRoomEntity) {
            return new SingleRoom.Builder(
                    roomEntity.getPricePerNight(), // oder einen Standardwert
                    roomIdentifier,
                    minimalHotel
            ).withId(roomEntity.getId()).build();
        } else if (roomEntity instanceof DoubleRoomEntity) {
            return new DoubleRoom.Builder(
                    roomEntity.getPricePerNight(), // oder einen Standardwert
                    roomIdentifier,
                    minimalHotel
            ).withId(roomEntity.getId()).build();
        } else {
            throw new RuntimeException("Unsupported Room subtype: " + roomEntity.getClass().getSimpleName());
        }
    }



}

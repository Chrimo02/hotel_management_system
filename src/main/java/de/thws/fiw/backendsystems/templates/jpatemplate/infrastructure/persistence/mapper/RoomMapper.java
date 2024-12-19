package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.DoubleRoom;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.SingleRoom;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;
@ApplicationScoped
public class RoomMapper {
    HotelMapper hotelMapper;
    RoomIdentifierMapper roomIdentifierMapper;
    @Inject
    public RoomMapper(HotelMapper hotelMapper, RoomIdentifierMapper roomIdentifierMapper) {
        this.hotelMapper = hotelMapper;
        this.roomIdentifierMapper = roomIdentifierMapper;
    }

    public Room entityToDomain(RoomEntity roomEntity) {
        Room room;
        if (roomEntity instanceof SingleRoomEntity) {
            room = new SingleRoom.Builder(roomEntity.getPricePerNight(),
                    roomIdentifierMapper.entityToDomain(roomEntity.getRoomIdentifier()),
                    hotelMapper.mapHotelEntityToDomainHotel(roomEntity.getHotel()))
                    .withId(roomEntity.getId())
                    .build();
            room.setBookings(bookingMapper.toDomainSet(roomEntity.getBookings()));
            return room;
        }
        if (roomEntity instanceof DoubleRoomEntity) {
            room = new DoubleRoom.Builder(roomEntity.getPricePerNight(),
                    roomIdentifierMapper.entityToDomain(roomEntity.getRoomIdentifier()),
                    hotelMapper.mapHotelEntityToDomainHotel(roomEntity.getHotel()))
                    .withId(roomEntity.getId())
                    .build();
            room.setBookings(bookingMapper.toDomainSet(roomEntity.getBookings()));
            return room;
        }
        return null;
    }
    public RoomEntity domainToEntity(Room room) {
        if (room instanceof SingleRoom){
            RoomEntity entity = new SingleRoomEntity(room.getId(),room.getPricePerNight(),roomIdentifierMapper.domainToEntity(room.getRoomIdentifier()),hotelMapper.mapDomainHotelToHotelEntity(room.getHotel()));
            entity.setAvailabilityMap(room.getAvailabilityMap());
            return entity;
        }
        else if (room instanceof DoubleRoom){
            RoomEntity entity = new DoubleRoomEntity(room.getId(), room.getPricePerNight(), roomIdentifierMapper.domainToEntity(room.getRoomIdentifier()), hotelMapper.mapDomainHotelToHotelEntity(room.getHotel()));
            entity.setAvailabilityMap(room.getAvailabilityMap());
            return entity;
        }
        return null;
    }

    public List<Room> toDomainList(List<RoomEntity> resultList )
    {
        if(resultList == null) return null;

        return resultList.stream( ).map( this::entityToDomain ).collect( Collectors.toList( ) );
    }
    public List<RoomEntity> toEntityList( List<Room> resultList )
    {
        if(resultList == null) return null;

        return resultList.stream( ).map( this::domainToEntity ).collect( Collectors.toList( ) );
    }
}

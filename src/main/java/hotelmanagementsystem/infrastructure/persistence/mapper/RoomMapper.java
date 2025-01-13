package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.infrastructure.persistence.entities.*;
import hotelmanagementsystem.infrastructure.persistence.entities.DoubleRoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.SingleRoomEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;
@ApplicationScoped
public class RoomMapper {
    HotelMapper hotelMapper;
    RoomIdentifierMapper roomIdentifierMapper;
    BookingMapper bookingMapper;

    @Inject
    public RoomMapper(HotelMapper hotelMapper, RoomIdentifierMapper roomIdentifierMapper, BookingMapper bookingMapper) {
        this.hotelMapper = hotelMapper;
        this.roomIdentifierMapper = roomIdentifierMapper;
        this.bookingMapper = bookingMapper;
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
            RoomEntity roomEntity = new SingleRoomEntity(room.getId(),room.getPricePerNight(),roomIdentifierMapper.domainToEntity(room.getRoomIdentifier()),hotelMapper.mapDomainHotelToHotelEntity(room.getHotel()));
            roomEntity.setBookings(bookingMapper.toEntitySet(room.getBookings()));
            return roomEntity;
        }
        else if (room instanceof DoubleRoom){
            RoomEntity roomEntity = new DoubleRoomEntity(room.getId(), room.getPricePerNight(), roomIdentifierMapper.domainToEntity(room.getRoomIdentifier()), hotelMapper.mapDomainHotelToHotelEntity(room.getHotel()));
            roomEntity.setBookings(bookingMapper.toEntitySet(room.getBookings()));
            return roomEntity;
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

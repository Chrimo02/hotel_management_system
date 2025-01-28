//package hotelmanagementsystem.infrastructure.api.mapper;
//
//import hotelmanagementsystem.domain.models.DoubleRoom;
//import hotelmanagementsystem.domain.models.Room;
//import hotelmanagementsystem.domain.models.SingleRoom;
//import hotelmanagementsystem.infrastructure.api.dto.RoomDTO;
//import hotelmanagementsystem.infrastructure.api.dto.SingleRoomDTO;
//import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
//import hotelmanagementsystem.infrastructure.persistence.mapper.HotelMapper;
//import hotelmanagementsystem.infrastructure.persistence.mapper.RoomIdentifierMapper;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//@ApplicationScoped
//public class RoomMapper {
//    HotelMapper hotelMapper;
//    RoomIdentifierMapper roomIdentifierMapper;
//    hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper bookingMapper;
//
//    @Inject
//    public RoomMapper(HotelMapper hotelMapper, RoomIdentifierMapper roomIdentifierMapper, BookingMapper bookingMapper) {
//        this.hotelMapper = hotelMapper;
//        this.roomIdentifierMapper = roomIdentifierMapper;
//        this.bookingMapper = bookingMapper;
//    }
//    public Room dtoToDomain(RoomDTO roomDTO) {
//        Room room;
//        if (roomDTO instanceof SingleRoomDTO) {
//            room = new SingleRoom.Builder(roomEntity.getPricePerNight(),
//                    roomIdentifierMapper.entityToDomain(roomEntity.getRoomIdentifier()),
//                    hotelMapper.mapHotelEntityToDomainHotel(roomEntity.getHotel()))
//                    .withId(roomEntity.getId())
//                    .build();
//            room.setBookings(bookingMapper.toDomainSet(roomEntity.getBookings()));
//            return room;
//        }
//        if (roomEntity instanceof DoubleRoomEntity) {
//            room = new DoubleRoom.Builder(roomEntity.getPricePerNight(),
//                    roomIdentifierMapper.entityToDomain(roomEntity.getRoomIdentifier()),
//                    hotelMapper.mapHotelEntityToDomainHotel(roomEntity.getHotel()))
//                    .withId(roomEntity.getId())
//                    .build();
//            room.setBookings(bookingMapper.toDomainSet(roomEntity.getBookings()));
//            return room;
//        }
//        return null;
//    }
//    public RoomEntity domainToDto(Room room) {
//        if (room instanceof SingleRoom){
//            RoomEntity roomEntity = new SingleRoomEntity(room.getId(),room.getPricePerNight(),roomIdentifierMapper.domainToEntity(room.getRoomIdentifier()),hotelMapper.mapDomainHotelToHotelEntity(room.getHotel()));
//            roomEntity.setBookings(bookingMapper.toEntitySet(room.getBookings()));
//            return roomEntity;
//        }
//        else if (room instanceof DoubleRoom){
//            RoomEntity roomEntity = new DoubleRoomEntity(room.getId(), room.getPricePerNight(), roomIdentifierMapper.domainToEntity(room.getRoomIdentifier()), hotelMapper.mapDomainHotelToHotelEntity(room.getHotel()));
//            roomEntity.setBookings(bookingMapper.toEntitySet(room.getBookings()));
//            return roomEntity;
//        }
//        return null;
//    }
//}

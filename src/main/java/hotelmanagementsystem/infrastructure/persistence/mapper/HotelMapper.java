package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HotelMapper {

    private final BookingMapper bookingMapper;
    private final HotelLocationMapper hotelLocationMapper;
    private final HotelRatingMapper hotelRatingMapper;
    private final RoomMapper roomMapper;
    @Inject
    public HotelMapper(BookingMapper bookingMapper, HotelLocationMapper hotelLocationMapper, HotelRatingMapper hotelRatingMapper,
                       RoomMapper roomMapper){
        this.bookingMapper = bookingMapper;
        this.hotelLocationMapper = hotelLocationMapper;
        this.hotelRatingMapper = hotelRatingMapper;
        this.roomMapper = roomMapper;

    }


    public HotelEntity mapDomainHotelToHotelEntity(Hotel hotel) {
        return new HotelEntity.HotelBuilder()
                .withId(hotel.getId())
                .withName(hotel.getName())
                .withDescription(hotel.getDescription())
                .withLocation(hotelLocationMapper.mapToEntity(hotel.getLocation()))
                .withBookings(bookingMapper.toEntityList(hotel.getBookings()))
                .withRatings(hotelRatingMapper.mapToEntityList(hotel.getRatings()))
                .withRooms(roomMapper.toEntityList(hotel.getRooms()))
                .build();
    }

    public Hotel mapHotelEntityToDomainHotel(HotelEntity entity) {
        return new Hotel.HotelBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .withLocation(hotelLocationMapper.mapToDomain(entity.getLocation()))
                .withBookingList(bookingMapper.toDomainList(entity.getBookings()))
                .withRoomsList(roomMapper.toDomainList(entity.getRooms()))
                .withRatingMap(hotelRatingMapper.mapToDomainList(entity.getRatings()))
                .build();
    }



}

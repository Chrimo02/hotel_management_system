package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class HotelMapper {

    private final BookingMapper bookingMapper;
    private final HotelLocationMapper hotelLocationMapper;
    private final RoomMapper roomMapper;
    private final HotelRatingMapper hotelRatingMapper;

    @Inject
    public HotelMapper(
            BookingMapper bookingMapper,
            HotelLocationMapper hotelLocationMapper,
            RoomMapper roomMapper,
            HotelRatingMapper hotelRatingMapper
    ) {
        this.bookingMapper = bookingMapper;
        this.hotelLocationMapper = hotelLocationMapper;
        this.roomMapper = roomMapper;
        this.hotelRatingMapper = hotelRatingMapper;
    }

    public HotelEntity mapDomainHotelToHotelEntity(Hotel hotel) {
        if (hotel == null) return null;

        return new HotelEntity.HotelBuilder()
                .withId(hotel.getId())
                .withName(hotel.getName())
                .withDescription(hotel.getDescription())
                .withLocation(hotelLocationMapper.mapToEntity(hotel.getLocation()))
                .withBookings(bookingMapper.toEntityList(hotel.getBookings()))
                .withRooms(roomMapper.toEntityList(hotel.getRooms()))
                .withRatings(hotelRatingMapper.mapToEntityList(hotel.getRatings()))
                .withAverageRating(hotel.getAverageRating())
                .build();
    }

    public Hotel mapHotelEntityToDomainHotel(HotelEntity entity) {
        if (entity == null) return null;

        List<Booking> domainBookings = bookingMapper.toDomainList(entity.getBookings());
        List<Room> domainRooms = roomMapper.toDomainList(entity.getRooms());
        List<HotelRating> domainRatings = hotelRatingMapper.mapToDomainList(entity.getRatings());

        return new Hotel.HotelBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .withLocation(hotelLocationMapper.mapToDomain(entity.getLocation()))
                .withBookingList(domainBookings)
                .withRoomsList(domainRooms)
                .withRatingMap(domainRatings)
                .withAverageRating(entity.getAverageRating())
                .build();
    }
}
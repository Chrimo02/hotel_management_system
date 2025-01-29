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
    private final HotelRatingMapper hotelRatingMapper;
    private final RoomMapper roomMapper;

    @Inject
    public HotelMapper(
            BookingMapper bookingMapper,
            HotelLocationMapper hotelLocationMapper,
            HotelRatingMapper hotelRatingMapper,
            RoomMapper roomMapper
    ) {
        this.bookingMapper = bookingMapper;
        this.hotelLocationMapper = hotelLocationMapper;
        this.hotelRatingMapper = hotelRatingMapper;
        this.roomMapper = roomMapper;
    }

    // Domain -> Entity
    public HotelEntity mapDomainHotelToHotelEntity(Hotel hotel) {
        if (hotel == null) return null;

        return new HotelEntity.HotelBuilder()
                .withId(hotel.getId())
                .withName(hotel.getName())
                .withDescription(hotel.getDescription())
                .withLocation(hotelLocationMapper.mapToEntity(hotel.getLocation()))
                .withBookings(bookingMapper.toEntityList(hotel.getBookings()))
                .withRatings(hotelRatingMapper.mapToEntityList(hotel.getRatings()))
                .withRooms(roomMapper.toEntityList(hotel.getRooms()))
                // averageRating kann man optional übernehmen
                .build();
    }

    // Entity -> Domain
    public Hotel mapHotelEntityToDomainHotel(HotelEntity entity) {
        if (entity == null) return null;

        List<Booking> domainBookings = bookingMapper.toDomainList(entity.getBookings());
        List<HotelRating> domainRatings = hotelRatingMapper.mapToDomainList(entity.getRatings());
        List<Room> domainRooms = roomMapper.toDomainList(entity.getRooms());

        return new Hotel.HotelBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .withLocation(hotelLocationMapper.mapToDomain(entity.getLocation()))
                .withBookingList(domainBookings)
                .withRoomsList(domainRooms)
                .withRatingMap(domainRatings)
                // averageRating hättest du in entity.getAverageRating()
                // => hotel.setAverageRating(...)
                .build();
    }
}

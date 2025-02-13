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

    // Neu: Wir injecten jetzt bewusst HotelRatingMapper,
    // damit wir beim Laden eines Hotels alle Ratings mappen können.
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

    /**
     * Domain -> Entity.
     * Wenn du auch die Ratings des Hotels in der DB updaten/speichern willst,
     * rufst du hier hotelRatingMapper.mapToEntityList(...) auf.
     * So werden auch neue/aktualisierte Ratings in der DB persistiert.
     */
    public HotelEntity mapDomainHotelToHotelEntity(Hotel hotel) {
        if (hotel == null) return null;

        return new HotelEntity.HotelBuilder()
                .withId(hotel.getId())
                .withName(hotel.getName())
                .withDescription(hotel.getDescription())
                .withLocation(hotelLocationMapper.mapToEntity(hotel.getLocation()))
                .withBookings(bookingMapper.toEntityList(hotel.getBookings()))
                .withRooms(roomMapper.toEntityList(hotel.getRooms()))
                // Wichtig: Ratings aus dem Domain-Hotel in Entities umwandeln.
                .withRatings(hotelRatingMapper.mapToEntityList(hotel.getRatings()))
                .withAverageRating(hotel.getAverageRating())
                .build();
    }

    /**
     * Entity -> Domain.
     * Hier fügen wir das Mapping der Ratings hinzu,
     * damit das Domain-Hotel am Ende ein "hotel.getRatings()" hat.
     */
    public Hotel mapHotelEntityToDomainHotel(HotelEntity entity) {
        if (entity == null) return null;

        // Buchungen
        List<Booking> domainBookings = bookingMapper.toDomainList(entity.getBookings());
        // Rooms
        List<Room> domainRooms = roomMapper.toDomainList(entity.getRooms());
        // Ratings
        List<HotelRating> domainRatings = hotelRatingMapper.mapToDomainList(entity.getRatings());

        return new Hotel.HotelBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .withLocation(hotelLocationMapper.mapToDomain(entity.getLocation()))
                .withBookingList(domainBookings)
                .withRoomsList(domainRooms)
                // Hier werden nun die Ratings dem Hotel hinzugefügt.
                .withRatingMap(domainRatings)
                .withAverageRating(entity.getAverageRating())
                .build();
    }
}
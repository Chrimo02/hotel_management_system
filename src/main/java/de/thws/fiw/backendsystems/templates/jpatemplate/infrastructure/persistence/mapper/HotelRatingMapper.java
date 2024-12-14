package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRating;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.HotelRatingEnum;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelRatingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class HotelRatingMapper {

    private final BookingMapper bookingMapper;
    private final HotelMapper hotelMapper;

    @Inject
    public HotelRatingMapper(BookingMapper bookingMapper, HotelMapper hotelMapper){
        this.bookingMapper = bookingMapper;
        this.hotelMapper = hotelMapper;

    }

    public Map<Long, HotelRating> mapToDomainMap(Map<Long, HotelRatingEntity> hotelRatingEntities) {
        return hotelRatingEntities.entrySet() // Zugriff auf die Key-Value-Paare der Map
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,          // Übernimm den Key unverändert
                        entry -> mapToDomain(entry.getValue()) // Wandele den Value um
                ));
    }

    public Map<Long, HotelRatingEntity> mapToEntityMap(Map<Long, HotelRating> hotelRatings) {
        return hotelRatings.entrySet() // Zugriff auf alle Key-Value-Paare
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,    // Schlüssel unverändert übernehmen
                        entry -> mapToEntity(entry.getValue()) // Wert konvertieren
                ));
    }


    public HotelRating mapToDomain(HotelRatingEntity entity) {
        // Erstellen der Domain-Instanz basierend auf der Entity
        return new HotelRating.Builder()
                .withRating(entity.getStarRating())
                .withId(entity.getId())
                .withComment(entity.getCommentRating())
                .withBooking(bookingMapper.bookingEntityToBooking(entity.getBooking()))
                .build();

    }

    public HotelRatingEntity mapToEntity(HotelRating rating) {
        // Erstellen der Domain-Instanz basierend auf der Entity
        return new HotelRatingEntity.Builder()
                .withRating(rating.getStarRating())
                .withBooking(bookingMapper.bookingToBookingEntity(rating.getBooking()))
                .withId(rating.getId())
                .withComment(rating.getGuestComment())
                .withHotel(hotelMapper.mapDomainHotelToHotelEntity(rating.getBooking().getHotel()))
                .build();

    }
}

package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelRatingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class HotelRatingMapper {

    private final GuestMapper guestMapper;

    @Inject
    public HotelRatingMapper(GuestMapper guestMapper) {
        this.guestMapper = guestMapper;
    }
    public HotelRating mapToDomain(HotelRatingEntity entity) {
        if (entity == null) {
            return null;
        }

        Guest domainGuest = guestMapper.guestEntityToGuest(entity.getGuest());

        return new HotelRating.Builder()
                .withId(entity.getId())
                .withHotel(null)
                .withGuest(domainGuest)
                .withRating(entity.getStarRating())
                .withComment(entity.getCommentRating())
                .build();
    }

    public HotelRatingEntity mapToEntity(HotelRating rating) {
        if (rating == null) {
            return null;
        }

        GuestEntity guestEntity = guestMapper.guestToGuestEntity(rating.getGuest());

        return new HotelRatingEntity.Builder()
                .withId(rating.getId())
                .withRating(rating.getStarRating())
                .withComment(rating.getGuestComment())
                .withGuest(guestEntity)
                .build();
    }

    public List<HotelRating> mapToDomainList(List<HotelRatingEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    public List<HotelRatingEntity> mapToEntityList(List<HotelRating> ratings) {
        if (ratings == null) {
            return Collections.emptyList();
        }
        return ratings.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }
}

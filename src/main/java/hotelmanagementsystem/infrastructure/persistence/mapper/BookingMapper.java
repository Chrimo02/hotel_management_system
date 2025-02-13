package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingMapper {

    private final GuestMapper guestMapper;
    private final RoomMapper roomMapper;

    @Inject
    public BookingMapper(GuestMapper guestMapper, RoomMapper roomMapper) {
        this.guestMapper = guestMapper;
        this.roomMapper = roomMapper;
    }

    public BookingEntity bookingToBookingEntity(Booking booking) {
        if (booking == null) return null;

        HotelEntity minimalHotelEntity = null;
        if (booking.getHotel() != null && booking.getHotel().getId() != null) {
            minimalHotelEntity = new HotelEntity.HotelBuilder()
                    .withId(booking.getHotel().getId())
                    .build();
        }

        List<RoomEntity> roomEntities = roomMapper.toEntityList(booking.getRooms());

        List<GuestEntity> guestEntities = guestMapper.guestsToGuestEntities(booking.getGuests());

        BookingEntity bookingEntity = new BookingEntity(
                booking.getId(),
                minimalHotelEntity,
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                roomEntities,
                guestEntities,
                booking.getStatus(),
                booking.getCheckInTime(),
                booking.getCheckOutTime()
        );

        return bookingEntity;
    }

    public Booking bookingEntityToBooking(BookingEntity bookingEntity) {
        if (bookingEntity == null) return null;

        Hotel hotelDomain = null;
        if (bookingEntity.getHotel() != null) {
            hotelDomain = new Hotel.HotelBuilder()
                    .withId(bookingEntity.getHotel().getId())
                    .withName(bookingEntity.getHotel().getName())
                    .build();
        }

        List<Room> rooms = roomMapper.toDomainList(bookingEntity.getRooms());
        List<Guest> guests = guestMapper.guestEntitiesToGuests(bookingEntity.getGuests());

        Booking domainBooking = new Booking(
                bookingEntity.getId(),
                hotelDomain,
                bookingEntity.getCheckInDate(),
                bookingEntity.getCheckOutDate(),
                rooms,
                guests,
                bookingEntity.isStatus(),
                bookingEntity.getCheckInTime(),
                bookingEntity.getCheckOutTime()
        );

        return domainBooking;
    }

    public List<Booking> toDomainList(List<BookingEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::bookingEntityToBooking)
                .collect(Collectors.toList());
    }
    public List<BookingEntity> toEntityList(List<Booking> bookings) {
        if (bookings == null) return null;
        return bookings.stream()
                .map(this::bookingToBookingEntity)
                .collect(Collectors.toList());
    }
}

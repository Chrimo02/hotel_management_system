package hotelmanagementsystem.infrastructure.persistence.mapper;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingMapper {
    private final GuestMapper guestMapper;
    private final RoomMapper roomMapper;
    private final HotelMapper hotelMapper;

    @Inject
    public BookingMapper(GuestMapper guestMapper, RoomMapper roomMapper, HotelMapper hotelMapper) {
        this.guestMapper = guestMapper;
        this.roomMapper = roomMapper;
        this.hotelMapper = hotelMapper;
    }

    public BookingEntity bookingToBookingEntity (Booking booking) {
        if(booking == null) return null;

        List<GuestEntity> guestEntities = guestMapper.guestsToGuestEntities(booking.getGuests());
        List<RoomEntity> roomEntities = roomMapper.toEntityList(booking.getRooms());
        HotelEntity hotelEntity = hotelMapper.mapDomainHotelToHotelEntity(booking.getHotel());

        return new BookingEntity(
                booking.getId(),
                hotelEntity,
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                roomEntities,
                guestEntities,
                booking.getStatus(),
                booking.getCheckInTime(),
                booking.getCheckOutTime()
        );
    }

    public Booking bookingEntityToBooking(BookingEntity bookingEntity) {
        if(bookingEntity == null) return null;

        List<Guest> guests = guestMapper.guestEntitiesToGuests(bookingEntity.getGuests());
        List<Room> rooms = roomMapper.toDomainList(bookingEntity.getRooms());
        Hotel hotel = hotelMapper.mapHotelEntityToDomainHotel(bookingEntity.getHotel());

        return new Booking(
                bookingEntity.getId(),
                hotel,
                bookingEntity.getCheckInDate(),
                bookingEntity.getCheckOutDate(),
                rooms,
                guests,
                bookingEntity.isStatus(),
                bookingEntity.getCheckInTime(),
                bookingEntity.getCheckOutTime()
        );
    }
    public List<Booking> toDomainList( List<BookingEntity> resultList )
    {
        if(resultList == null) return null;

        return resultList.stream( ).map( this::bookingEntityToBooking ).collect( Collectors.toList( ) );
    }
    public List<BookingEntity> toEntityList( List<Booking> resultList )
    {
        if(resultList == null) return null;

        return resultList.stream( ).map( this::bookingToBookingEntity ).collect( Collectors.toList( ) );
    }
    public Set<BookingEntity> toEntitySet(Set<Booking> bookings) {
        if (bookings == null) return null;
        return bookings.stream().map(this::bookingToBookingEntity).collect(Collectors.toSet());
    }

    public Set<Booking> toDomainSet(Set<BookingEntity> bookingEntities) {
        if (bookingEntities == null) return null;
        return bookingEntities.stream().map(this::bookingEntityToBooking).collect(Collectors.toSet());
    }
}

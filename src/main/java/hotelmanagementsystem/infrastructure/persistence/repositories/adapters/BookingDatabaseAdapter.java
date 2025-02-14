package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;


import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.BookingDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.GuestMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.BookingRepository;
import hotelmanagementsystem.domain.models.Hotel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingDatabaseAdapter implements BookingRepository {
    private final BookingDAO bookingDAO;
    private final RoomMapper roomMapper;
    private final GuestMapper guestMapper;
    private final HotelMapper hotelMapper;
    private final BookingMapper bookingMapper;

    @Inject
    public BookingDatabaseAdapter(BookingDAO bookingDAO, RoomMapper roomMapper, GuestMapper guestMapper, HotelMapper hotelMapper, BookingMapper bookingMapper) {
        this.bookingDAO = bookingDAO;
        this.roomMapper = roomMapper;
        this.guestMapper = guestMapper;
        this.hotelMapper = hotelMapper;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Booking createBooking(Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate, List<Room> rooms, List<Guest> guests) {

        List<RoomEntity> roomEntities = roomMapper.toEntityList(rooms);
        List<GuestEntity> guestEntities = guestMapper.guestsToGuestEntities(guests);
        HotelEntity hotelEntity = hotelMapper.mapDomainHotelToHotelEntity(hotel);

        BookingEntity bookingEntity = new BookingEntity(hotelEntity, checkInDate, checkOutDate, roomEntities, guestEntities);

        return bookingMapper.bookingEntityToBooking(bookingDAO.create(bookingEntity));

    }

    @Override
    public void updateBooking(Booking booking) {

        BookingEntity bookingEntity = bookingDAO.read(booking.getId());

        bookingEntity.setRooms(roomMapper.toEntityList(booking.getRooms()));
        bookingEntity.setStatus(booking.getStatus());
        bookingEntity.setCheckInTime(booking.getCheckInTime());
        bookingEntity.setCheckOutTime(booking.getCheckOutTime());

        bookingDAO.update(bookingEntity);
    }

    @Override
    public Booking getBookingById(long bookingId) {
        BookingEntity bookingEntity = bookingDAO.read(bookingId);

        List<Room> rooms = roomMapper.toDomainList(bookingEntity.getRooms());
        List<Guest> guests = guestMapper.guestEntitiesToGuests(bookingEntity.getGuests());
        Hotel hotel = hotelMapper.mapHotelEntityToDomainHotel(bookingEntity.getHotel());

        return new Booking(bookingEntity.getId(), hotel, bookingEntity.getCheckInDate(), bookingEntity.getCheckOutDate(), rooms, guests, bookingEntity.isStatus(), bookingEntity.getCheckInTime(), bookingEntity.getCheckOutTime());
    }

    @Override
    public List<Booking> findBookingsByCheckInDate(LocalDate checkInDate) {
        List<BookingEntity> bookingEntities = bookingDAO.findBookingsByCheckInDate(checkInDate);
        return bookingEntities.stream()
                .map(bookingMapper::bookingEntityToBooking)
                .collect(Collectors.toList());
    }

}

package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;


import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.BookingDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.HotelEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.BookingMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.HotelMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.RoomMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.GuestMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.BookingRepository;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.GuestRepository;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.RoomRepository;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Hotel;
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

    //can only change status, checkintime and checkouttime as everything other than that is permanent.
    @Override
    public void updateBooking(Booking booking) {

        BookingEntity bookingEntity = bookingDAO.read(booking.getId());

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
}

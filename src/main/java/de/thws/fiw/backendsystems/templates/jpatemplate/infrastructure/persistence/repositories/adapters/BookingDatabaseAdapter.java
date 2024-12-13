package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters;


import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.BookingDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.BookingRepository;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.GuestRepository;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.RoomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingDatabaseAdapter implements BookingRepository {
    private final BookingDAO bookingDAO;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    @Inject
    public BookingDatabaseAdapter(BookingDAO bookingDAO, RoomRepository roomRepository, GuestRepository guestRepository) {
        this.bookingDAO = bookingDAO;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public void createBooking(long hotelId, LocalDate checkInDate, LocalDate checkOutDate, List<Room> rooms, List<Guest> guests) {

        //how to convert a Room to RoomEntity or Guest to GuestEntity here?
        List<RoomEntity> roomEntities = rooms.stream()
                .map(room -> roomRepository.toRoomEntity(room))
                .collect(Collectors.toList());

        List<GuestEntity> guestEntities = guests.stream()
                .map(guest -> guestRepository.toGuestEntity(guest))
                .collect(Collectors.toList());

        BookingEntity bookingEntity = new BookingEntity(hotelId, checkInDate, checkOutDate, roomEntities, guestEntities);

        bookingDAO.create(bookingEntity);
    }

    //can only change status, checkintime and checkoutime as everything other than that is permanent.
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

        //gleiches problem wie oben. vielleicht ein mapper package einführen?
        List<Room> rooms = bookingEntity.getRooms().stream()
                .map(room -> roomRepository.toRoomModel(room))
                .collect(Collectors.toList());

        List<Guest> guests = bookingEntity.getGuests().stream()
                .map(guest -> guestRepository.toGuestModel(guest))
                .collect(Collectors.toList());

        return new Booking(bookingEntity.getId(), bookingEntity.getHotelId(), bookingEntity.getCheckInDate(), bookingEntity.getCheckOutDate(), rooms, guests, bookingEntity.isStatus(), bookingEntity.getCheckInTime(), bookingEntity.getCheckOutTime());
    }
}

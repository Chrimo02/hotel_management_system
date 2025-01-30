package repositoriesTest;
/*

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.GuestDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.GuestMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.adapters.GuestDatabaseAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GuestRepoTest {

    @Mock
    private GuestDAO guestDAO;

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private GuestMapper guestMapper;

    @InjectMocks
    private GuestDatabaseAdapter guestDatabaseAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateGuest() {
        Guest guest = new Guest.GuestBuilder().withId(1L).withFirstName("John").build();
        GuestEntity guestEntity = new GuestEntity();
        when(guestMapper.guestToGuestEntity(guest)).thenReturn(guestEntity);

        guestDatabaseAdapter.createGuest(guest);

        verify(guestDAO, times(1)).create(guestEntity);
    }

    @Test
    void testUpdateEmail() {
        Guest guest = new Guest.GuestBuilder().withId(1L).withEMail("newemail@example.com").build();
        GuestEntity guestEntity = new GuestEntity();
        when(guestDAO.read(guest.getId())).thenReturn(guestEntity);

        guestDatabaseAdapter.updateGuest(guest);

        assertEquals("newemail@example.com", guestEntity.geteMail());
        verify(guestDAO, times(1)).update(guestEntity);
    }

    @Test
    void testUpdatePhone() {
        Guest guest = new Guest.GuestBuilder().withId(1L).withPhoneNumber("1234567890").build();
        GuestEntity guestEntity = new GuestEntity();
        when(guestDAO.read(guest.getId())).thenReturn(guestEntity);

        guestDatabaseAdapter.updateGuest(guest);

        assertEquals("1234567890", guestEntity.getPhoneNumber());
        verify(guestDAO, times(1)).update(guestEntity);
    }

    @Test
    void testUpdateLastName() {
        Guest guest = new Guest.GuestBuilder().withId(1L).withLastName("Doe").build();
        GuestEntity guestEntity = new GuestEntity();
        when(guestDAO.read(guest.getId())).thenReturn(guestEntity);

        guestDatabaseAdapter.updateGuest(guest);

        assertEquals("Doe", guestEntity.getLastName());
        verify(guestDAO, times(1)).update(guestEntity);
    }

    @Test
    void testUpdateTitle() {
        Guest guest = new Guest.GuestBuilder().withId(1L).withTitle("Dr.").build();
        GuestEntity guestEntity = new GuestEntity();
        when(guestDAO.read(guest.getId())).thenReturn(guestEntity);

        guestDatabaseAdapter.updateGuest(guest);

        assertEquals("Dr.", guestEntity.getTitle());
        verify(guestDAO, times(1)).update(guestEntity);
    }

    @Test
    void testDeleteGuest() {
        Guest guest = new Guest.GuestBuilder().withId(1L).build();
        GuestEntity guestEntity = new GuestEntity();
        when(guestMapper.guestToGuestEntity(guest)).thenReturn(guestEntity);

        guestDatabaseAdapter.deleteGuest(guest);

        verify(guestDAO, times(1)).delete(guestEntity);
    }

    @Test
    void testGetGuestById() {
        long id = 1L;
        GuestEntity guestEntity = new GuestEntity();
        guestEntity.setId(id);
        guestEntity.setFirstName("John");
        guestEntity.setLastName("Doe");
        guestEntity.setTitle("Mr.");
        guestEntity.seteMail("john.doe@example.com");
        guestEntity.setPhoneNumber("1234567890");
        guestEntity.setBirthday(LocalDate.of(1990, 1, 1));
        when(guestDAO.read(id)).thenReturn(guestEntity);

        Guest guest = guestDatabaseAdapter.getGuestById(id);

        assertNotNull(guest);
        assertEquals(id, guest.getId());
        assertEquals("John", guest.getFirstName());
        assertEquals("Doe", guest.getLastName());
        assertEquals("Mr.", guest.getTitle());
        assertEquals("john.doe@example.com", guest.geteMail());
        assertEquals("1234567890", guest.getPhoneNumber());
    }

    @Test
    void testGetBookingsByGuestId() {
        long guestId = 1L;
        BookingEntity bookingEntity = mock(BookingEntity.class); // Mock BookingEntity
        Booking booking = mock(Booking.class); // Mock Booking
        when(guestDAO.findBookingsByGuestId(guestId)).thenReturn(List.of(bookingEntity));
        when(bookingMapper.bookingEntityToBooking(bookingEntity)).thenReturn(booking);

        List<Booking> bookings = guestDatabaseAdapter.getBookingsByGuestId(guestId);

        assertNotNull(bookings);
        assertEquals(1, bookings.size());
        assertEquals(booking, bookings.get(0));
    }



}


 */

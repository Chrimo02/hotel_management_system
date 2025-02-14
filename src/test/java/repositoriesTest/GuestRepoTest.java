package repositoriesTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.GuestDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.GuestMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.adapters.GuestDatabaseAdapter;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GuestRepoTest {

    @Mock
    private GuestDAO guestDAO;
    @Mock
    private BookingMapper bookingMapper;
    @Mock
    private GuestMapper guestMapper;

    @InjectMocks
    private GuestDatabaseAdapter adapter;

    private Guest dummyGuest;
    private GuestEntity dummyGuestEntity;
    private Booking dummyBooking;
    private BookingEntity dummyBookingEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dummyGuest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("Alice")
                .withLastName("Smith")
                .withBirthday(1990, 1, 1)
                .withEMail("alice@example.com")
                .withPhoneNumber("123456789")
                .build();
        dummyGuestEntity = new GuestEntity("Alice", "Smith", 1990, 1, 1, "alice@example.com", "123456789");
        dummyGuestEntity.setId(dummyGuest.getId());

        // Für getBookingsByGuestId
        dummyBooking = mock(Booking.class);
        dummyBookingEntity = mock(BookingEntity.class);
    }

    @Test
    public void testCreateGuest() {
        when(guestMapper.guestToGuestEntity(dummyGuest)).thenReturn(dummyGuestEntity);
        when(guestDAO.create(dummyGuestEntity)).thenReturn(dummyGuestEntity);
        when(guestMapper.guestEntityToGuest(dummyGuestEntity)).thenReturn(dummyGuest);

        Guest result = adapter.createGuest(dummyGuest);
        assertNotNull(result);
        assertEquals(dummyGuest.getId(), result.getId());
    }

    @Test
    public void testUpdateGuest() {
        when(guestDAO.read(dummyGuest.getId())).thenReturn(dummyGuestEntity);
        // Simuliere, dass beim Update das GuestEntity zurückgegeben wird
        when(guestDAO.update(dummyGuestEntity)).thenReturn(dummyGuestEntity);
        when(guestMapper.guestEntityToGuest(dummyGuestEntity)).thenReturn(dummyGuest);

        // Ändere ein Feld
        dummyGuest.seteMail("newalice@example.com");
        Guest updated = adapter.updateGuest(dummyGuest);
        assertNotNull(updated);
        // Da wir den Mapper stubben, erwarten wir den gleichen Dummy
        assertEquals(dummyGuest.geteMail(), updated.geteMail());
    }

    @Test
    public void testDeleteGuest() {
        when(guestMapper.guestToGuestEntity(dummyGuest)).thenReturn(dummyGuestEntity);
        // Kein Rückgabewert beim Delete
        adapter.deleteGuest(dummyGuest);
        verify(guestDAO, times(1)).delete(dummyGuestEntity);
    }

    @Test
    public void testGetGuestById() {
        when(guestDAO.read(dummyGuest.getId())).thenReturn(dummyGuestEntity);
        when(guestMapper.guestEntityToGuest(dummyGuestEntity)).thenReturn(dummyGuest);
        Guest result = adapter.getGuestById(dummyGuest.getId());
        assertNotNull(result);
        assertEquals(dummyGuest.getId(), result.getId());
    }

    @Test
    public void testGetBookingsByGuestId() {
        List<BookingEntity> bookingEntities = Collections.singletonList(dummyBookingEntity);
        when(guestDAO.findBookingsByGuestId(dummyGuest.getId())).thenReturn(bookingEntities);
        when(bookingMapper.bookingEntityToBooking(dummyBookingEntity)).thenReturn(dummyBooking);
        List<Booking> bookings = adapter.getBookingsByGuestId(dummyGuest.getId());
        assertNotNull(bookings);
        assertEquals(1, bookings.size());
        assertEquals(dummyBooking, bookings.get(0));
    }
}

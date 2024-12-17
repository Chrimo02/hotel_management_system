package repositoriesTest;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.GuestDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.BookingMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.GuestMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters.GuestDatabaseAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestRepoTest {

    @Mock
    private GuestDAO guestDAO;

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private GuestMapper guestMapper;

    @InjectMocks
    private GuestDatabaseAdapter guestDatabaseAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGuest() {
        Guest guest = new Guest.GuestBuilder().withFirstName("John").build();
        GuestEntity guestEntity = new GuestEntity();
        when(guestMapper.guestToGuestEntity(guest)).thenReturn(guestEntity);
        doNothing().when(guestDAO).create(guestEntity);

        guestDatabaseAdapter.createGuest(guest);

        verify(guestMapper, times(1)).guestToGuestEntity(guest);
        verify(guestDAO, times(1)).create(guestEntity);
    }

    @Test
    public void testUpdateEmail() {
        GuestEntity guestEntity = new GuestEntity();
        when(guestDAO.read(1L)).thenReturn(guestEntity);
        doNothing().when(guestDAO).update(guestEntity);

        guestDatabaseAdapter.updateEmail(1L, "new.email@example.com");

        assertEquals("new.email@example.com", guestEntity.geteMail());
        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).update(guestEntity);
    }

    @Test
    public void testUpdatePhone() {
        GuestEntity guestEntity = new GuestEntity();
        when(guestDAO.read(1L)).thenReturn(guestEntity);
        doNothing().when(guestDAO).update(guestEntity);

        guestDatabaseAdapter.updatePhone(1L, "1234567890");

        assertEquals("1234567890", guestEntity.getPhoneNumber());
        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).update(guestEntity);
    }

    @Test
    public void testUpdateLastName() {
        GuestEntity guestEntity = new GuestEntity();
        when(guestDAO.read(1L)).thenReturn(guestEntity);
        doNothing().when(guestDAO).update(guestEntity);

        guestDatabaseAdapter.updateLastName(1L, "Doe");

        assertEquals("Doe", guestEntity.getLastName());
        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).update(guestEntity);
    }

    @Test
    public void testUpdateTitle() {
        GuestEntity guestEntity = new GuestEntity();
        when(guestDAO.read(1L)).thenReturn(guestEntity);
        doNothing().when(guestDAO).update(guestEntity);

        guestDatabaseAdapter.updateTitle(1L, "Dr.");

        assertEquals("Dr.", guestEntity.getTitle());
        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).update(guestEntity);
    }

    @Test
    public void testDeleteGuest() {
        GuestEntity guestEntity = new GuestEntity();
        when(guestDAO.read(1L)).thenReturn(guestEntity);
        doNothing().when(guestDAO).delete(guestEntity);

        guestDatabaseAdapter.deleteGuest(1L);

        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).delete(guestEntity);
    }

    @Test
    public void testGetGuestById() {
        GuestEntity guestEntity = new GuestEntity();
        guestEntity.setFirstName("John");
        guestEntity.setLastName("Doe");
        guestEntity.setTitle("Mr.");
        guestEntity.setBirthday(LocalDate.of(1990, 5, 20));
        guestEntity.seteMail("john.doe@example.com");
        guestEntity.setPhoneNumber("1234567890");
        guestEntity.setBookingsHistory(new ArrayList<>());

        when(guestDAO.read(1L)).thenReturn(guestEntity);
        when(bookingMapper.toDomainList(guestEntity.getBookingsHistory())).thenReturn(new ArrayList<>());

        Guest guest = guestDatabaseAdapter.getGuestById(1L);

        assertNotNull(guest);
        assertEquals("John", guest.getFirstName());
        assertEquals("Doe", guest.getLastName());
        assertEquals("Mr.", guest.getTitle());
        assertEquals(LocalDate.of(1990, 5, 20), guest.getBirthday());
        assertEquals("john.doe@example.com", guest.geteMail());
        assertEquals("1234567890", guest.getPhoneNumber());
        assertTrue(guest.getBookingsHistory().isEmpty());

        verify(guestDAO, times(1)).read(1L);
        verify(bookingMapper, times(1)).toDomainList(anyList());
    }
}


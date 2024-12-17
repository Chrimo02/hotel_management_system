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
import java.util.Collections;
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

    private Guest mockGuest;
    private GuestEntity mockGuestEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Guest und GuestEntity
        mockGuest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("John")
                .withLastName("Doe")
                .withTitle("Mr.")
                .withBirthday(1990, 5, 20)
                .withEMail("john.doe@example.com")
                .withPhoneNumber("1234567890")
                .withBookingsHistory(Collections.emptyList())
                .build();

        mockGuestEntity = new GuestEntity();
        mockGuestEntity.setId(1L);
        mockGuestEntity.setFirstName("John");
        mockGuestEntity.setLastName("Doe");
        mockGuestEntity.setTitle("Mr.");
        mockGuestEntity.setBirthday(LocalDate.of(1990, 5, 20));
        mockGuestEntity.seteMail("john.doe@example.com");
        mockGuestEntity.setPhoneNumber("1234567890");
        mockGuestEntity.setBookingsHistory(Collections.emptyList());
    }

    @Test
    public void testCreateGuest_Success() {
        when(guestMapper.guestToGuestEntity(mockGuest)).thenReturn(mockGuestEntity);

        guestDatabaseAdapter.createGuest(mockGuest);

        verify(guestMapper, times(1)).guestToGuestEntity(mockGuest);
        verify(guestDAO, times(1)).create(mockGuestEntity);
    }

    @Test
    public void testUpdateEmail_Success() {
        when(guestDAO.read(1L)).thenReturn(mockGuestEntity);

        mockGuestEntity.seteMail("new.email@example.com");
        when(mockGuest.geteMail()).thenReturn("new.email@example.com");

        guestDatabaseAdapter.updateEmail(mockGuest);

        assertEquals("new.email@example.com", mockGuestEntity.geteMail());
        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).update(mockGuestEntity);
    }

    @Test
    public void testUpdatePhone_Success() {
        when(guestDAO.read(1L)).thenReturn(mockGuestEntity);

        mockGuestEntity.setPhoneNumber("9876543210");
        when(mockGuest.getPhoneNumber()).thenReturn("9876543210");

        guestDatabaseAdapter.updatePhone(mockGuest);

        assertEquals("9876543210", mockGuestEntity.getPhoneNumber());
        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).update(mockGuestEntity);
    }

    @Test
    public void testUpdateLastName_Success() {
        when(guestDAO.read(1L)).thenReturn(mockGuestEntity);

        mockGuestEntity.setLastName("Smith");
        when(mockGuest.getLastName()).thenReturn("Smith");

        guestDatabaseAdapter.updateLastName(mockGuest);

        assertEquals("Smith", mockGuestEntity.getLastName());
        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).update(mockGuestEntity);
    }

    @Test
    public void testUpdateTitle_Success() {
        when(guestDAO.read(1L)).thenReturn(mockGuestEntity);

        mockGuestEntity.setTitle("Dr.");
        when(mockGuest.getTitle()).thenReturn("Dr.");

        guestDatabaseAdapter.updateTitle(mockGuest);

        assertEquals("Dr.", mockGuestEntity.getTitle());
        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).update(mockGuestEntity);
    }

    @Test
    public void testDeleteGuest_Success() {
        when(guestDAO.read(1L)).thenReturn(mockGuestEntity);

        guestDatabaseAdapter.deleteGuest(1L);

        verify(guestDAO, times(1)).read(1L);
        verify(guestDAO, times(1)).delete(mockGuestEntity);
    }

    @Test
    public void testGetGuestById_Success() {
        when(guestDAO.read(1L)).thenReturn(mockGuestEntity);
        when(bookingMapper.toDomainList(mockGuestEntity.getBookingsHistory())).thenReturn(Collections.emptyList());

        Guest result = guestDatabaseAdapter.getGuestById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
        verify(guestDAO, times(1)).read(1L);
        verify(bookingMapper, times(1)).toDomainList(mockGuestEntity.getBookingsHistory());
    }

    @Test
    public void testGetGuestById_NullEntity() {
        when(guestDAO.read(1L)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> guestDatabaseAdapter.getGuestById(1L));
        verify(guestDAO, times(1)).read(1L);
    }
}


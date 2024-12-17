package serviceTest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.GuestNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Booking;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.services.GuestService;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGuest() {
        doNothing().when(guestRepository).createGuest(any(Guest.class));

        guestService.createGuest("John", "Doe", "Mr.", 1990, 5, 20, "john.doe@example.com", "1234567890");

        verify(guestRepository, times(1)).createGuest(any(Guest.class));
    }

    @Test
    public void testGetNotNullGuestWhenGuestExists() throws GuestNotFoundException {
        Guest mockGuest = new Guest.GuestBuilder().withFirstName("John").build();
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);

        Guest result = guestService.getGuestById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void testGetNotNullGuestWhenGuestDoesNotExist() {
        when(guestRepository.getGuestById(1L)).thenReturn(null);

        assertThrows(GuestNotFoundException.class, () -> guestService.getGuestById(1L));
    }

    @Test
    public void testGetAllBookingsFromGuest() throws GuestNotFoundException {
        Guest mockGuest = new Guest.GuestBuilder().withFirstName("John").build();
        List<Booking> mockBookings = new ArrayList<>();
        mockGuest.setBookingsHistory(mockBookings);
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);

        List<Booking> result = guestService.getAllBookingsFromGuest(1L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUpdateEmail() throws GuestNotFoundException {
        Guest mockGuest = new Guest.GuestBuilder().withFirstName("John").build();
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);
        doNothing().when(guestRepository).updateEmail(1L, "new.email@example.com");

        guestService.updateEMail(1L, "new.email@example.com");

        verify(guestRepository, times(1)).updateEmail(1L, "new.email@example.com");
        assertEquals("new.email@example.com", mockGuest.geteMail());
    }

    @Test
    public void testUpdatePhone() throws GuestNotFoundException {
        Guest mockGuest = new Guest.GuestBuilder().withFirstName("John").build();
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);
        doNothing().when(guestRepository).updatePhone(1L, "9876543210");

        guestService.updatePhone(1L, "9876543210");

        verify(guestRepository, times(1)).updatePhone(1L, "9876543210");
        assertEquals("9876543210", mockGuest.getPhoneNumber());
    }

    @Test
    public void testUpdateLastName() throws GuestNotFoundException {
        Guest mockGuest = new Guest.GuestBuilder().withFirstName("John").build();
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);
        doNothing().when(guestRepository).updateLastName(1L, "Smith");

        guestService.updateLastName(1L, "Smith");

        verify(guestRepository, times(1)).updateLastName(1L, "Smith");
        assertEquals("Smith", mockGuest.getLastName());
    }

    @Test
    public void testUpdateTitle() throws GuestNotFoundException {
        Guest mockGuest = new Guest.GuestBuilder().withFirstName("John").build();
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);
        doNothing().when(guestRepository).updateTitle(1L, "Dr.");

        guestService.updateTitle(1L, "Dr.");

        verify(guestRepository, times(1)).updateTitle(1L, "Dr.");
        assertEquals("Dr.", mockGuest.getTitle());
    }

    @Test
    public void testDeleteGuest() throws GuestNotFoundException {
        Guest mockGuest = new Guest.GuestBuilder().withFirstName("John").build();
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);
        doNothing().when(guestRepository).deleteGuest(1L);

        guestService.deleteGuest(1L);

        verify(guestRepository, times(1)).deleteGuest(1L);
    }
}

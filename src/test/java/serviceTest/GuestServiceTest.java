package serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GuestServiceTest {

    @InjectMocks
    private GuestService guestService;

    @Mock
    private GuestRepository guestRepository;

    private Guest dummyGuest;

    @BeforeEach
    public void setUp() {
        dummyGuest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 1, 1)
                .withEMail("john@example.com")
                .withPhoneNumber("123456789")
                .build();
    }

    @Test
    public void testCreateGuest() {
        when(guestRepository.createGuest(any(Guest.class))).thenReturn(dummyGuest);
        Guest result = guestService.createGuest("John", "Doe", 1990, 1, 1, "john@example.com", "123456789");
        assertEquals(dummyGuest, result);
        verify(guestRepository).createGuest(any(Guest.class));
    }

    @Test
    public void testGetGuestById_Success() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(dummyGuest);
        Guest result = guestService.getGuestById(1L);
        assertEquals(dummyGuest, result);
        verify(guestRepository).getGuestById(1L);
    }

    @Test
    public void testGetGuestById_NotFound() {
        when(guestRepository.getGuestById(2L)).thenReturn(null);
        assertThrows(GuestNotFoundException.class, () -> guestService.getGuestById(2L));
        verify(guestRepository).getGuestById(2L);
    }

    @Test
    public void testUpdateEMail() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(dummyGuest);
        when(guestRepository.updateGuest(dummyGuest)).thenReturn(dummyGuest);
        Guest result = guestService.updateEMail(1L, "new@example.com");
        assertEquals("new@example.com", result.geteMail());
        verify(guestRepository).updateGuest(dummyGuest);
    }

    @Test
    public void testUpdatePhone() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(dummyGuest);
        when(guestRepository.updateGuest(dummyGuest)).thenReturn(dummyGuest);
        Guest result = guestService.updatePhone(1L, "987654321");
        assertEquals("987654321", result.getPhoneNumber());
        verify(guestRepository).updateGuest(dummyGuest);
    }

    @Test
    public void testUpdateLastName() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(dummyGuest);
        when(guestRepository.updateGuest(dummyGuest)).thenReturn(dummyGuest);
        Guest result = guestService.updateLastName(1L, "Smith");
        assertEquals("Smith", result.getLastName());
        verify(guestRepository).updateGuest(dummyGuest);
    }

    @Test
    public void testDeleteGuest() {
        when(guestRepository.getGuestById(1L)).thenReturn(dummyGuest);
        doNothing().when(guestRepository).deleteGuest(dummyGuest);
        assertDoesNotThrow(() -> guestService.deleteGuest(1L));
        verify(guestRepository).deleteGuest(dummyGuest);
    }

    @Test
    public void testLoadGuests_Success() {
        when(guestRepository.getGuestById(1L)).thenReturn(dummyGuest);
        when(guestRepository.getGuestById(2L)).thenReturn(dummyGuest);
        List<Guest> result = guestService.loadGuests(Arrays.asList(1L, 2L));
        assertEquals(2, result.size());
        verify(guestRepository, times(1)).getGuestById(1L);
        verify(guestRepository, times(1)).getGuestById(2L);
    }

    @Test
    public void testLoadGuests_NotFound() {
        when(guestRepository.getGuestById(1L)).thenReturn(null);
        Exception ex = assertThrows(RuntimeException.class, () -> guestService.loadGuests(Collections.singletonList(1L)));
        assertTrue(ex.getMessage().contains("Guest with Id 1 not found"));
    }
}

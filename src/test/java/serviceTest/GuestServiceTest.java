/*
package serviceTest;


import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestServiceTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestService guestService;

    @Mock
    private Guest mockGuest;

    @Mock
    private Booking mockBooking;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGuest_Success() {
        guestService.createGuest("John", "Doe", "Mr.", 1990, 5, 20, "john.doe@example.com", "1234567890");
        verify(guestRepository, times(1)).createGuest(any(Guest.class));
    }

    @Test
    public void testDeleteGuest_Success() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);

        guestService.deleteGuest(1L);

        verify(guestRepository, times(1)).deleteGuest(mockGuest);
    }

    @Test
    public void testDeleteGuest_GuestNotFound() {
        when(guestRepository.getGuestById(1L)).thenReturn(null);

        assertThrows(GuestNotFoundException.class, () -> guestService.deleteGuest(1L));
        verify(guestRepository, times(1)).getGuestById(1L);
        verify(guestRepository, never()).deleteGuest(any(Guest.class));
    }

    @Test
    public void testGetAllBookingsFromGuest_Success() {
        List<Booking> mockBookings = List.of(mockBooking);
        when(guestRepository.getBookingsByGuestId(1L)).thenReturn(mockBookings);

        List<Booking> result = guestService.getAllBookingsFromGuest(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockBookings, result);
        verify(guestRepository, times(1)).getBookingsByGuestId(1L);
    }

    @Test
    public void testUpdateEMail_Success() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);

        guestService.updateEMail(1L, "new.email@example.com");

        verify(mockGuest, times(1)).seteMail("new.email@example.com");
        verify(guestRepository, times(1)).updateEmail(mockGuest);
    }

    @Test
    public void testUpdatePhone_Success() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);

        guestService.updatePhone(1L, "9876543210");

        verify(mockGuest, times(1)).setPhoneNumber("9876543210");
        verify(guestRepository, times(1)).updatePhone(mockGuest);
    }

    @Test
    public void testUpdateLastName_Success() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);

        guestService.updateLastName(1L, "Smith");

        verify(mockGuest, times(1)).setLastName("Smith");
        verify(guestRepository, times(1)).updateLastName(mockGuest);
    }

    @Test
    public void testUpdateTitle_Success() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);

        guestService.updateTitle(1L, "Dr.");

        verify(mockGuest, times(1)).setTitle("Dr.");
        verify(guestRepository, times(1)).updateTitle(mockGuest);
    }

    @Test
    public void testGetGuestById_Success() throws GuestNotFoundException {
        when(guestRepository.getGuestById(1L)).thenReturn(mockGuest);

        Guest result = guestService.getGuestById(1L);

        assertEquals(mockGuest, result);
        verify(guestRepository, times(1)).getGuestById(1L);
    }

    @Test
    public void testGetGuestById_GuestNotFound() {
        when(guestRepository.getGuestById(1L)).thenReturn(null);

        assertThrows(GuestNotFoundException.class, () -> guestService.getGuestById(1L));
        verify(guestRepository, times(1)).getGuestById(1L);
    }

    @Test
    public void testLoadGuests_Success() {
        List<Long> guestIds = List.of(1L, 2L);
        Guest guest1 = mock(Guest.class);
        Guest guest2 = mock(Guest.class);

        when(guestRepository.getGuestById(1L)).thenReturn(guest1);
        when(guestRepository.getGuestById(2L)).thenReturn(guest2);

        List<Guest> result = guestService.loadGuests(guestIds);

        assertEquals(2, result.size());
        verify(guestRepository, times(1)).getGuestById(1L);
        verify(guestRepository, times(1)).getGuestById(2L);
    }

    @Test
    public void testLoadGuests_ThrowsRuntimeExceptionWhenGuestNotFound() {
        List<Long> guestIds = List.of(1L, 2L);

        when(guestRepository.getGuestById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> guestService.loadGuests(guestIds));
        verify(guestRepository, times(1)).getGuestById(1L);
    }
}

 */

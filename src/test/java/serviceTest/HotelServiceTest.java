/*
package serviceTest;

import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.domain.services.RoomService;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelLocationRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRatingRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelLocationRepository hotelLocationRepository;

    @Mock
    private HotelRatingRepository hotelRatingRepository;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private Hotel mockHotel;

    @Mock
    private HotelLocation mockHotelLocation;

    @Mock
    private HotelRating mockHotelRating;

    @Mock
    private Room mockRoom;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHotel_Success() {
        // Given
        String name = "Test Hotel";
        String description = "A new hotel for testing";
        HotelLocation location = mockHotelLocation;  // or a real instance if you prefer
        Hotel builtHotel = new Hotel.HotelBuilder()
                .withName(name)
                .withDescription(description)
                .withLocation(location)
                .build();

        // Stub the repository call
        when(hotelRepository.createHotel(any(Hotel.class))).thenAnswer(invocation -> {
            // Typically you might return the same object or a copy with an ID
            Hotel argHotel = invocation.getArgument(0);
            argHotel.setId(1L); // Simulate DB assigning an ID
            return argHotel;
        });

        // When
        Hotel createdHotel = hotelService.createHotel(name, description, location);

        // Then
        assertNotNull(createdHotel);
        assertEquals(name, createdHotel.getName());
        assertEquals(description, createdHotel.getDescription());
        assertEquals(location, createdHotel.getLocation());
        assertEquals(1L, createdHotel.getId());
        verify(hotelRepository).createHotel(any(Hotel.class));
    }

    @Test
    void testCreateHotel_InvalidName() {
        // Provide invalid name
        String invalidName = "";
        String description = "Desc";
        HotelLocation location = mockHotelLocation;

        // Then
        assertThrows(IllegalArgumentException.class,
                () -> hotelService.createHotel(invalidName, description, location),
                "Hotel name must not be empty");
        verify(hotelRepository, never()).createHotel(any(Hotel.class));
    }

    @Test
    void testDeleteHotel_Success() {
        // Given
        long hotelId = 100L;
        // Stub the repository so that 'findById' returns a hotel
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(mockHotel));

        // When
        boolean result = hotelService.deleteHotel(hotelId);

        // Then
        assertTrue(result);
        verify(hotelRepository).deleteById(hotelId);
    }

    @Test
    void testDeleteHotel_NotFound() {
        // Given
        long hotelId = 999L;
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // Then
        assertThrows(IllegalArgumentException.class, () -> hotelService.deleteHotel(hotelId));
        verify(hotelRepository, never()).deleteById(hotelId);
    }

    @Test
    void testUpdateHotel_Success() {
        // Given
        long hotelId = 1L;
        Map<String, String> updates = new HashMap<>();
        updates.put("name", "Updated Name");
        updates.put("description", "Updated Description");

        Hotel existingHotel = new Hotel.HotelBuilder()
                .withId(hotelId)
                .withName("Old Name")
                .withDescription("Old Description")
                .withLocation(mockHotelLocation)
                .build();

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(existingHotel));

        // When
        Hotel updatedHotel = hotelService.updateHotel(hotelId, updates);

        // Then
        assertNotNull(updatedHotel);
        assertEquals("Updated Name", updatedHotel.getName());
        assertEquals("Updated Description", updatedHotel.getDescription());
        verify(hotelRepository).update(existingHotel);
    }

    @Test
    void testUpdateHotel_NoChanges() {
        // Given
        long hotelId = 1L;
        Map<String, String> updates = new HashMap<>(); // no fields changed

        Hotel existingHotel = new Hotel.HotelBuilder()
                .withId(hotelId)
                .withName("Same Name")
                .withDescription("Same Description")
                .withLocation(mockHotelLocation)
                .build();

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(existingHotel));

        // When
        Hotel resultHotel = hotelService.updateHotel(hotelId, updates);

        // Then
        assertEquals(existingHotel, resultHotel, "Should return the same hotel, no changes made");
        verify(hotelRepository, never()).update(any(Hotel.class));
    }

    @Test
    void testGetHotels_Success() {
        // Given
        List<Hotel> mockHotels = List.of(mockHotel, mockHotel);
        when(hotelRepository.findAll()).thenReturn(mockHotels);

        // When
        List<Hotel> result = hotelService.getHotels();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(hotelRepository).findAll();
    }

    @Test
    void testGetHotelByHotelId_Success() {
        // Given
        long hotelId = 1L;
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(mockHotel));

        // When
        Hotel hotel = hotelService.getHotelByHotelId(hotelId);

        // Then
        assertNotNull(hotel);
        assertEquals(mockHotel, hotel);
        verify(hotelRepository).findById(hotelId);
    }

    @Test
    void testGetHotelByHotelId_NotFound() {
        // Given
        long hotelId = 999L;
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // Then
        assertThrows(IllegalArgumentException.class, () -> hotelService.getHotelByHotelId(hotelId));
        verify(hotelRepository).findById(hotelId);
    }

    @Test
    void testRateHotel_Success() {
        long hotelId = 1L;
        long guestId = 10L;

        // A mock hotel that has a booking with the given guestId
        Hotel hotel = new Hotel.HotelBuilder()
                .withId(hotelId)
                .withName("Test Hotel")
                .withLocation(mockHotelLocation)
                .withRatingMap(new ArrayList<>()) // start with empty ratings
                .build();

        // Mock booking data: the guest is in one booking in this hotel
        Guest mockGuest = mock(Guest.class);
        when(mockGuest.getId()).thenReturn(guestId);

        Booking mockBooking = mock(Booking.class);
        when(mockBooking.getGuests()).thenReturn(List.of(mockGuest));
        hotel.setBookings(List.of(mockBooking));

        // Stub
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        HotelRating rating = new HotelRating.Builder()
                .withId(guestId)
                .withRating(4)
                .withComment("Nice place")
                .build();

        // When
        hotelService.rateHotel(hotelId, guestId, rating);

        // Then
        assertEquals(1, hotel.getRatings().size(), "Should have 1 rating now");
        assertTrue(hotel.getRatings().contains(rating));
        verify(hotelRepository).update(hotel);
    }

    @Test
    void testRateHotel_GuestNotInHotel() {
        long hotelId = 2L;
        long guestId = 50L;

        Hotel hotel = new Hotel.HotelBuilder()
                .withId(hotelId)
                .withName("Test Hotel 2")
                .withBookingList(new ArrayList<>()) // no bookings
                .withRatingMap(new ArrayList<>())
                .build();

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));

        HotelRating rating = new HotelRating.Builder()
                .withId(guestId)
                .withRating(4)
                .withComment("Nice place")
                .build();

        // Then
        assertThrows(IllegalArgumentException.class, () -> hotelService.rateHotel(guestId, hotelId, rating));
        verify(hotelRepository, never()).update(any(Hotel.class));
    }
}

 */

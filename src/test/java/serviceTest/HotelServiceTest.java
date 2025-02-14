package serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.domain.models.PagedHotels;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.domain.services.RoomService;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelLocationRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRatingRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelLocationRepository hotelLocationRepository;

    @Mock
    private HotelRatingRepository hotelRatingRepository;

    @Mock
    private RoomService roomService;

    @Mock
    private GuestService guestService;

    @InjectMocks
    private HotelService hotelService;

    private HotelLocation testLocation;
    private Hotel testHotel;
    private List<HotelRating> testRatings;
    private List<Room> testRooms;
    private List<Booking> testBookings;
    private Guest testGuest;

    @BeforeEach
    void setUp() {
        testLocation = new HotelLocation.HotelLocationBuilder()
                .withId(1L)
                .withAddress("Teststraße 1")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();

        testRatings = new ArrayList<>();
        testRooms = new ArrayList<>();
        testBookings = new ArrayList<>();

        testHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("TestHotel")
                .withDescription("TestDescription")
                .withLocation(testLocation)
                .withRatingMap(testRatings)
                .withRoomsList(testRooms)
                .withBookingList(testBookings)
                .withAverageRating(0.0)
                .build();

        testGuest = new Guest.GuestBuilder()
                .withId(100L)
                .withFirstName("Max")
                .withLastName("Mustermann")
                .withBirthday(1990, 1, 1)
                .withEMail("max@example.com")
                .withPhoneNumber("0123456789")
                .build();
    }

    @Test
    void testCreateHotel_Success() throws Exception {
        // Stub: Valid inputs
        when(hotelRepository.createHotel(any(Hotel.class))).thenReturn(testHotel);

        Hotel created = hotelService.createHotel("TestHotel", "TestDescription", testLocation);

        assertNotNull(created);
        assertEquals("TestHotel", created.getName());
        verify(hotelRepository, times(1)).createHotel(any(Hotel.class));
    }

    @Test
    void testCreateHotel_InvalidInput() {
        // Da die Validierung nur auf null prüft, erwarten wir eine Exception, wenn der Name null ist.
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> hotelService.createHotel(null, "TestDescription", testLocation));
        assertTrue(exception.getMessage().contains("Hotel name must not be empty"));
    }

    @Test
    void testDeleteHotel_Success() throws Exception {
        when(hotelRepository.findById(1L)).thenReturn(testHotel);
        // Da deleteById eine void-Methode ist, genügt doNothing()
        doNothing().when(hotelRepository).deleteById(1L);

        boolean result = hotelService.deleteHotel(1L);

        assertTrue(result);
        verify(hotelRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteHotel_NotFound() {
        when(hotelRepository.findById(1L)).thenReturn(null);
        HotelNotFoundException exception = assertThrows(HotelNotFoundException.class,
                () -> hotelService.deleteHotel(1L));
        assertEquals("There is no Hotel with the specified ID!", exception.getMessage());
    }

    @Test
    void testUpdateHotel_Success() throws Exception {
        // Setup: Hotel mit altem Namen und Description
        Hotel hotelToUpdate = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("OldName")
                .withDescription("OldDesc")
                .withLocation(testLocation)
                .withRoomsList(testRooms)
                .withBookingList(testBookings)
                .withRatingMap(testRatings)
                .withAverageRating(3.0)
                .build();

        when(hotelRepository.findById(1L)).thenReturn(hotelToUpdate);
        // Falls update() nicht void ist, stubbieren wir es so:
        when(hotelRepository.update(any(Hotel.class))).thenReturn(null);

        Map<String, String> updates = new HashMap<>();
        updates.put("name", "NewName");
        updates.put("description", "NewDesc");

        Hotel updatedHotel = hotelService.updateHotel(1L, updates);

        assertEquals("NewName", updatedHotel.getName());
        assertEquals("NewDesc", updatedHotel.getDescription());
        verify(hotelRepository, times(1)).update(updatedHotel);
    }

    @Test
    void testListHotelsFilteredAndPaged() {
        // Erstelle Dummy-Daten
        List<Hotel> hotelList = Collections.singletonList(testHotel);
        PagedHotels pagedHotels = new PagedHotels(hotelList, 1);

        when(hotelRepository.findPagedByFilter("TestCity", 3.0, 1, 10))
                .thenReturn(pagedHotels);

        PagedHotels result = hotelService.listHotelsFilteredAndPaged("TestCity", 3.0, 1, 10);

        assertNotNull(result);
        assertEquals(1, result.getHotels().size());
        assertEquals(1, result.getTotalCount());
    }

    @Test
    void testGetHotelByHotelId_Success() throws Exception {
        when(hotelRepository.findById(1L)).thenReturn(testHotel);

        Hotel result = hotelService.getHotelByHotelId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetHotelByHotelId_NotFound() {
        when(hotelRepository.findById(1L)).thenReturn(null);

        HotelNotFoundException exception = assertThrows(HotelNotFoundException.class,
                () -> hotelService.getHotelByHotelId(1L));
        assertEquals("There is no Hotel with the specified ID!", exception.getMessage());
    }

    @Test
    void testRateHotel_Success() throws Exception {
        // Setup: Hotel ohne Ratings
        Hotel hotelForRating = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("TestHotel")
                .withDescription("Desc")
                .withLocation(testLocation)
                .withRoomsList(testRooms)
                .withBookingList(testBookings)
                .withRatingMap(new ArrayList<>())
                .withAverageRating(0.0)
                .build();

        when(hotelRepository.findById(1L)).thenReturn(hotelForRating);
        when(guestService.getGuestById(100L)).thenReturn(testGuest);
        when(hotelRepository.update(any(Hotel.class))).thenReturn(null);

        hotelService.rateHotel(100L, 1L, 5, "Excellent");

        // Prüfe: Das Rating wurde hinzugefügt und averageRating neu berechnet
        assertFalse(hotelForRating.getRatings().isEmpty());
        assertEquals(5.0, hotelForRating.getAverageRating());
        verify(hotelRepository, times(1)).update(hotelForRating);
    }

    @Test
    void testFindAvailableRooms() throws Exception {
        // Setup: Erstelle Hotel mit einem verfügbaren Raum
        Room availableRoom = mock(Room.class);
        // Entferne das unnötige Stubbing von getPricePerNight()
        when(roomService.isAvailable(eq(availableRoom), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(true);
        List<Room> rooms = Collections.singletonList(availableRoom);

        Hotel hotelWithRoom = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("TestHotel")
                .withDescription("Desc")
                .withLocation(testLocation)
                .withRoomsList(rooms)
                .withBookingList(new ArrayList<>())
                .withRatingMap(new ArrayList<>())
                .withAverageRating(0.0)
                .build();

        when(hotelRepository.findById(1L)).thenReturn(hotelWithRoom);

        List<Room> result = hotelService.findAvailableRooms(1L, null);

        assertNotNull(result);
        assertEquals(1, result.size());
    }


    @Test
    void testFilterHotelRatings() throws Exception {
        // Setup: Hotel mit zwei Ratings, eines mit Kommentar, eines ohne
        HotelRating rating1 = new HotelRating.Builder()
                .withId(10L)
                .withRating(4)
                .withComment("Good")
                .withGuest(testGuest)
                .withHotel(testHotel)
                .build();

        HotelRating rating2 = new HotelRating.Builder()
                .withId(11L)
                .withRating(4)
                .withComment("")
                .withGuest(testGuest)
                .withHotel(testHotel)
                .build();

        List<HotelRating> ratings = new ArrayList<>();
        ratings.add(rating1);
        ratings.add(rating2);

        Hotel hotelWithRatings = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("TestHotel")
                .withDescription("Desc")
                .withLocation(testLocation)
                .withRoomsList(new ArrayList<>())
                .withBookingList(new ArrayList<>())
                .withRatingMap(ratings)
                .withAverageRating(4.0)
                .build();

        when(hotelRepository.findById(1L)).thenReturn(hotelWithRatings);

        List<HotelRating> filtered = hotelService.filterHotelRatings(1L, 4, true);

        // Nur rating1 hat einen Kommentar
        assertEquals(1, filtered.size());
        assertEquals("Good", filtered.get(0).getGuestComment());
    }
}

package domainModelsTest;

import hotelmanagementsystem.domain.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelTest {

    @Mock
    private HotelLocation mockLocation;

    @Mock
    private Room mockRoom;

    @Mock
    private Booking mockBooking;

    @Mock
    private HotelRating mockRating;

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        // Initialize the @Mock fields
        MockitoAnnotations.openMocks(this);

        // Example: mock some methods if needed
        when(mockRoom.getPricePerNight()).thenReturn(150.0);

        // Prepare lists for the builder
        List<Room> rooms = new ArrayList<>();
        rooms.add(mockRoom);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(mockBooking);

        List<HotelRating> ratings = new ArrayList<>();
        ratings.add(mockRating);

        // Build the hotel using the builder
        hotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Mock Hotel")
                .withDescription("Mock Description")
                .withLocation(mockLocation)
                .withRoomsList(rooms)
                .withBookingList(bookings)
                .withRatingMap(ratings)
                .build();
    }

    @Test
    void testHotelConstruction() {
        // Verify initial state from builder
        // Note: As written in your original Hotel class, "id" isn't assigned in the constructor,
        // so getId() may be null unless you explicitly add `this.id = hotelBuilder.id;` in the Hotel constructor.
        // Assuming you did so:
        assertEquals(1L, hotel.getId());
        assertEquals("Mock Hotel", hotel.getName());
        assertEquals("Mock Description", hotel.getDescription());
        assertEquals(mockLocation, hotel.getLocation());
        assertNotNull(hotel.getRooms(), "Rooms list should not be null");
        assertNotNull(hotel.getBookings(), "Bookings list should not be null");
        assertNotNull(hotel.getRatings(), "Ratings list should not be null");
    }

    @Test
    void testSetAndGetId() {
        hotel.setId(42L);
        assertEquals(42L, hotel.getId(), "Hotel ID should be updated to 42");
    }

    @Test
    void testSetName() {
        hotel.setName("New Hotel Name");
        assertEquals("New Hotel Name", hotel.getName(), "Hotel name should be updated");
    }

    @Test
    void testSetAndGetDescription() {
        hotel.setDescription("New Description");
        assertEquals("New Description", hotel.getDescription());
    }

    @Test
    void testSetAndGetAverageRating() {
        hotel.setAverageRating(4.5);
        assertEquals(4.5, hotel.getAverageRating(), 0.0001, "Average rating should be set to 4.5");
    }

    @Test
    void testSetLocation() {
        HotelLocation newLocation = mock(HotelLocation.class);
        hotel.setLocation(newLocation);
        assertEquals(newLocation, hotel.getLocation(), "Hotel location should be updated to the new one");
    }

    @Test
    void testAddRating() {
        // Initially, we have one rating from setUp
        assertEquals(1, hotel.getRatings().size(), "Should start with one rating");

        HotelRating anotherMockRating = mock(HotelRating.class);
        hotel.addRating(anotherMockRating);

        assertEquals(2, hotel.getRatings().size(), "Now should have two ratings");
        assertTrue(hotel.getRatings().contains(anotherMockRating), "Ratings list should contain the newly added rating");
    }

    @Test
    void testAddRoom() {
        // Initially, we have one room from setUp
        assertEquals(1, hotel.getRooms().size(), "Should start with one room");

        Room newRoom = mock(Room.class);
        hotel.addRoom(newRoom);

        assertEquals(2, hotel.getRooms().size(), "Now should have two rooms");
        assertTrue(hotel.getRooms().contains(newRoom), "Rooms list should contain the newly added room");
    }

    @Test
    void testSetRooms() {
        List<Room> newRoomList = new ArrayList<>();
        hotel.setRoom(newRoomList);
        assertSame(newRoomList, hotel.getRooms(), "Hotel rooms list should reference the newly set list");
    }

    @Test
    void testSetBookings() {
        List<Booking> newBookingList = new ArrayList<>();
        hotel.setBookings(newBookingList);
        assertSame(newBookingList, hotel.getBookings(), "Hotel bookings list should reference the newly set list");
    }
}

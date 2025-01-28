//nur zum test grpc auskommentiert

package serviceTest;
import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.domain.services.RoomService;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomIdentifierRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HotelService hotelService;

    @Mock
    private RoomIdentifierRepository roomIdentifierRepository;

    @InjectMocks
    private RoomService roomService;

    private Room testRoom;
    private Hotel testHotel;

    @BeforeEach
    void setUp() {
        // **Initialize Mockito Mocks**
        MockitoAnnotations.openMocks(this);



        // Initialize the test room
        testRoom = new SingleRoom.Builder(100.0, new RoomIdentifier("Building A", 1, "101"), testHotel)
                .withId(1L)
                .build();
        List<Room> rooms = new ArrayList<>();
        rooms.add(testRoom);
        // Initialize the test hotel and rooms list
        testHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withRoomsList(rooms)
                .withName("Test Hotel")
                .build();

        // **Mock behavior for RoomRepository and HotelService**
        when(roomRepository.findRoomById(1L)).thenReturn(testRoom);
        when(hotelService.getHotelByHotelId(1L)).thenReturn(testHotel);
    }
    @Test
    void testGetRoomById() throws RoomNotFoundException {
        // Mock the repository response to return the test room when queried by ID
        when(roomRepository.findRoomById(1L)).thenReturn(testRoom);

        // Call the actual method to retrieve the room by ID
        Room result = roomService.getRoomById(1L);

        // Assertions
        assertNotNull(result);
        assertEquals(testRoom, result);
    }

    @Test
    void testBookRooms() {
        Booking booking = new Booking(1L, testHotel, LocalDate.now(), LocalDate.now().plusDays(2), List.of(testRoom), List.of(), true, null, null);

        roomService.bookRooms(booking);

        verify(roomRepository, times(1)).updateRoom(testRoom);
        assertTrue(testRoom.getBookings().contains(booking));
    }
    @Test
    void testAddBooking() {
        // Create a new booking
        Booking booking = new Booking(1L, testHotel, LocalDate.now(), LocalDate.now().plusDays(2), List.of(testRoom), List.of(), true, null, null);

        // Use a TreeSet to store bookings
        Set<Booking> treeSetBookings = new TreeSet<>();
        testRoom.setBookings(treeSetBookings);

        // Add the booking to the TreeSet
        testRoom.getBookings().add(booking);

        // Verify that the booking is added
        assertTrue(testRoom.getBookings().contains(booking));
        assertEquals(1, testRoom.getBookings().size());
        assertEquals(booking, testRoom.getBookings().iterator().next()); // Check if the booking is the first element
    }
    @Test
    void testDeleteFromTreeSet() throws RoomNotFoundException, BookingNotFoundException {
        Booking booking = new Booking(1L, testHotel, LocalDate.now(), LocalDate.now().plusDays(2), List.of(testRoom), List.of(), true, null, null);

        // Use a TreeSet to store bookings
        Set<Booking> treeSetBookings = new TreeSet<>();
        treeSetBookings.add(booking);
        testRoom.setBookings(treeSetBookings);

        when(roomRepository.findRoomById(1L)).thenReturn(testRoom);

        roomService.cancelRoom(1L, LocalDate.now(), LocalDate.now().plusDays(2));

        verify(roomRepository, times(1)).updateRoom(testRoom);
        assertFalse(testRoom.getBookings().contains(booking));
    }
    @Test
    void testCreateRoom() {
        RoomIdentifier roomIdentifier = new RoomIdentifier("BuildingA", 1, "101A");

        when(hotelService.getHotelByHotelId(1L)).thenReturn(testHotel);

        roomService.createRoom(200.0, roomIdentifier, 1L, SingleRoom.class);

        verify(roomRepository, times(1)).saveRoom(any(SingleRoom.class));
        verify(roomIdentifierRepository, times(1)).saveRoomIdentifier(roomIdentifier);
    }
    @Test
    void testFindAvailableRooms() {
        // Initialize room list in the test hotel to prevent NullPointerException
        testHotel.setRoom(new ArrayList<>());
        testHotel.getRooms().add(testRoom);

        // Create a booking that covers a certain date range
        Booking booking = new Booking(1L, testHotel, LocalDate.now(), LocalDate.now().plusDays(2), List.of(testRoom), List.of(), true, null, null);

        // Set bookings on the test room
        testRoom.setBookings(new HashSet<>(List.of(booking)));

        // Mock HotelService response to return testHotel
        when(hotelService.getHotelByHotelId(1L)).thenReturn(testHotel);

        // Call method under test for a date range that does not overlap with the existing booking
        List<Room> availableRooms = roomService.findAvailableRooms(1L, List.of(SingleRoom.class), LocalDate.now().plusDays(3), LocalDate.now().plusDays(4));

        // Assertions
        assertNotNull(availableRooms);
        assertFalse(availableRooms.isEmpty()); // Expect at least one available room
        assertTrue(availableRooms.contains(testRoom)); // Expect the testRoom to be included
    }

}



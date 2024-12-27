package serviceTest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.BookingNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions.RoomNotFoundException;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.services.HotelService;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.services.RoomService;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.RoomIdentifierRepository;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        MockitoAnnotations.openMocks(this);

        testHotel = new Hotel.HotelBuilder()
                .withName("Test Hotel")
                .build();

        testRoom = new SingleRoom.Builder(150.0, new RoomIdentifier("BuildingA", 1, "101A"), testHotel)
                .withId(1L)
                .build();

        testHotel.setRoom(List.of(testRoom));
    }

    @Test
    void testGetRoomById() throws RoomNotFoundException {
        when(roomRepository.findRoomById(1L)).thenReturn(testRoom);

        Room result = roomService.findAvailableRooms(testHotel.getId(), List.of(SingleRoom.class), LocalDate.now(), LocalDate.now().plusDays(1)).get(0);
        //create method should return the id
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
        Booking booking = new Booking(1L, testHotel, LocalDate.now(), LocalDate.now().plusDays(2), List.of(testRoom), List.of(), true, null, null);
        testRoom.setBookings(Set.of(booking));

        when(hotelService.getHotelByHotelId(1L)).thenReturn(testHotel);

        List<Room> availableRooms = roomService.findAvailableRooms(1L, List.of(SingleRoom.class), LocalDate.now().plusDays(3), LocalDate.now().plusDays(4));

        assertFalse(availableRooms.isEmpty());
        assertTrue(availableRooms.contains(testRoom));
    }
}

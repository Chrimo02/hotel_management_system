package serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;
import java.util.List;

import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.domain.services.RoomService;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomIdentifierRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HotelService hotelService;

    @Mock
    private RoomIdentifierRepository roomIdentifierRepository;

    private Hotel dummyHotel;
    private RoomIdentifier dummyIdentifier;
    private Room dummyRoom;
    private Booking dummyBooking;

    @BeforeEach
    public void setUp() {
        dummyIdentifier = new RoomIdentifier("Test Building", 1, "101");
        dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Desc")
                .withLocation(null)
                .withRoomsList(new ArrayList<>())
                .build();
        dummyRoom = new SingleRoom.Builder(100.0, dummyIdentifier, dummyHotel)
                .withId(10L)
                .build();
        dummyBooking = new Booking(1L, dummyHotel,
                LocalDate.now().plusDays(2), LocalDate.now().plusDays(5),
                Collections.singletonList(dummyRoom), new ArrayList<>(), false, null, null);
    }

    @Test
    public void testGetRoomById_Success() throws RoomNotFoundException {
        when(roomRepository.findRoomById(10L)).thenReturn(dummyRoom);
        Room result = roomService.getRoomById(10L);
        assertEquals(dummyRoom, result);
        verify(roomRepository).findRoomById(10L);
    }

    @Test
    public void testGetRoomById_NotFound() {
        when(roomRepository.findRoomById(20L)).thenReturn(null);
        assertThrows(RoomNotFoundException.class, () -> roomService.getRoomById(20L));
        verify(roomRepository).findRoomById(20L);
    }

    @Test
    public void testBookRooms_Success() {
        dummyRoom.setBookings(new TreeSet<>());
        roomService.bookRooms(dummyBooking);
        assertTrue(dummyRoom.getBookings().contains(dummyBooking));
        verify(roomRepository).updateRoom(dummyRoom);
    }

    @Test
    public void testCancelRoom_Success() throws RoomNotFoundException, Exception {
        dummyRoom.setBookings(new TreeSet<>(Arrays.asList(dummyBooking)));
        when(roomRepository.findRoomById(10L)).thenReturn(dummyRoom);
        roomService.cancelRoom(10L, dummyBooking);
        assertFalse(dummyRoom.getBookings().contains(dummyBooking));
        verify(roomRepository).updateRoom(dummyRoom);
    }

    @Test
    public void testCancelRoom_BookingNotFound() {
        dummyRoom.setBookings(new TreeSet<>());
        when(roomRepository.findRoomById(10L)).thenReturn(dummyRoom);

        assertDoesNotThrow(() -> roomService.cancelRoom(10L, dummyBooking));

        assertFalse(dummyRoom.getBookings().contains(dummyBooking));
    }


    @Test
    public void testIsAvailable_NoOverlap() {
        dummyRoom.setBookings(new TreeSet<>());
        boolean available = roomService.isAvailable(dummyRoom, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));
        assertTrue(available);
    }

    @Test
    public void testIsAvailable_WithOverlap() {
        dummyRoom.setBookings(new TreeSet<>(Arrays.asList(dummyBooking)));
        boolean available = roomService.isAvailable(dummyRoom, LocalDate.now().plusDays(3), LocalDate.now().plusDays(6));
        assertFalse(available);
    }

    @Test
    public void testCreateRoom_SingleRoom() throws Exception {
        when(hotelService.getHotelByHotelId(1L)).thenReturn(dummyHotel);
        when(roomRepository.saveRoom(any(Room.class))).thenAnswer(invocation -> invocation.getArgument(0));
        RoomIdentifier identifier = new RoomIdentifier("New Building", 2, "202");
        Room createdRoom = roomService.createRoom(200.0, identifier, 1L, SingleRoom.class);
        assertNotNull(createdRoom);
        verify(roomRepository).saveRoom(any(Room.class));
        verify(roomIdentifierRepository).saveRoomIdentifier(identifier);
    }

    @Test
    public void testCreateRoom_DoubleRoom() throws Exception {
        when(hotelService.getHotelByHotelId(1L)).thenReturn(dummyHotel);
        when(roomRepository.saveRoom(any(Room.class))).thenAnswer(invocation -> invocation.getArgument(0));
        RoomIdentifier identifier = new RoomIdentifier("Another Building", 3, "303");
        Room createdRoom = roomService.createRoom(250.0, identifier, 1L, DoubleRoom.class);
        assertNotNull(createdRoom);
        verify(roomRepository).saveRoom(any(Room.class));
        verify(roomIdentifierRepository).saveRoomIdentifier(identifier);
    }

    @Test
    public void testRemoveRoom_NoActiveBookings() {
        dummyRoom.setBookings(new TreeSet<>());
        when(roomRepository.findRoomById(10L)).thenReturn(dummyRoom);
        assertDoesNotThrow(() -> roomService.removeRoom(10L));
        verify(roomRepository).removeRoom(10L);
    }

    @Test
    public void testRemoveRoom_WithActiveBookings() {
        dummyBooking.setStatus(true);
        dummyRoom.setBookings(new TreeSet<>(Arrays.asList(dummyBooking)));
        when(roomRepository.findRoomById(10L)).thenReturn(dummyRoom);
        Exception ex = assertThrows(RuntimeException.class, () -> roomService.removeRoom(10L));
        assertTrue(ex.getMessage().contains("Cannot remove room with active bookings"));
    }

    @Test
    public void testUpdatePricePerNight_Success() throws RoomNotFoundException {
        when(roomRepository.findRoomById(10L)).thenReturn(dummyRoom);
        dummyRoom.setPricePerNight(100.0);
        Room updatedRoom = roomService.updatePricePerNight(10L, 150.0);
        assertEquals(150.0, updatedRoom.getPricePerNight(), 0.001);
        verify(roomRepository).updateRoom(dummyRoom);
    }

    @Test
    public void testUpdatePricePerNight_RoomNotFound() {
        when(roomRepository.findRoomById(20L)).thenReturn(null);
        assertThrows(RoomNotFoundException.class, () -> roomService.updatePricePerNight(20L, 150.0));
    }

    @Test
    public void testUpdateRoomIdentifier_Success() throws RoomNotFoundException {
        when(roomRepository.findRoomById(10L)).thenReturn(dummyRoom);
        RoomIdentifier newIdentifier = new RoomIdentifier("Updated Building", 4, "404");
        Room updatedRoom = roomService.updateRoomIdentifier(10L, newIdentifier);
        assertEquals(newIdentifier, updatedRoom.getRoomIdentifier());
        verify(roomIdentifierRepository).saveRoomIdentifier(newIdentifier);
        verify(roomRepository).updateRoom(dummyRoom);
    }

    @Test
    public void testUpdateRoomIdentifier_RoomNotFound() {
        when(roomRepository.findRoomById(20L)).thenReturn(null);
        RoomIdentifier newIdentifier = new RoomIdentifier("Updated Building", 4, "404");
        assertThrows(RoomNotFoundException.class, () -> roomService.updateRoomIdentifier(20L, newIdentifier));
    }

    @Test
    public void testFindAvailableRooms_Success() throws Exception {
        dummyRoom.setBookings(new TreeSet<>());
        List<Room> roomList = Arrays.asList(dummyRoom);
        Hotel hotelWithRoom = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Desc")
                .withLocation(null)
                .withRoomsList(roomList)
                .build();
        when(hotelService.getHotelByHotelId(1L)).thenReturn(hotelWithRoom);

        List<Room> result = roomService.findAvailableRooms(
                1L,
                Collections.singletonList(dummyRoom.getClass()),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3)
        );
        assertNotNull(result);
        assertTrue(result.contains(dummyRoom));
    }
}

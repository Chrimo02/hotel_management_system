package repositoriesTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.RoomDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.SingleRoomEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomIdentifierMapper;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.adapters.RoomDatabaseAdapter;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class RoomRepoTest {

    @Mock
    private RoomDAO roomDAO;
    @Mock
    private HotelDAO hotelDAO;
    @Mock
    private RoomMapper roomMapper;
    @Mock
    private RoomIdentifierMapper roomIdentifierMapper;
    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private RoomDatabaseAdapter adapter;

    private Room dummyRoom;
    private RoomEntity dummyRoomEntity;
    private Hotel dummyHotel;
    private HotelEntity dummyHotelEntity;
    private RoomIdentifier dummyRoomIdentifier;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();
        dummyRoomIdentifier = new RoomIdentifier("BuildingX", 3, "303X");
        dummyRoom = new SingleRoom.Builder(150.0, dummyRoomIdentifier, dummyHotel)
                .withId(10L)
                .build();
        dummyRoomEntity = new SingleRoomEntity(10L, 150.0,
                roomIdentifierMapper.domainToEntity(dummyRoomIdentifier), null);
        dummyHotelEntity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName(dummyHotel.getName())
                .build();
    }

    @Test
    public void testFindRoomById() {
        when(roomDAO.read(10L)).thenReturn(dummyRoomEntity);
        when(roomMapper.entityToDomain(dummyRoomEntity)).thenReturn(dummyRoom);
        Room result = adapter.findRoomById(10L);
        assertNotNull(result);
        assertEquals(dummyRoom.getId(), result.getId());
    }

    @Test
    public void testSaveRoom() {
        when(roomMapper.domainToEntity(dummyRoom)).thenReturn(dummyRoomEntity);
        when(roomDAO.create(dummyRoomEntity)).thenReturn(dummyRoomEntity);
        when(roomMapper.entityToDomain(dummyRoomEntity)).thenReturn(dummyRoom);
        Room result = adapter.saveRoom(dummyRoom);
        assertNotNull(result);
        assertEquals(dummyRoom.getId(), result.getId());
    }

    @Test
    public void testRemoveRoom() {
        // Erzeuge ein Mock für das RoomEntity
        RoomEntity dummyRoomEntity = mock(RoomEntity.class);

        // Stub: roomDAO.read(10L) soll unser dummyRoomEntity liefern
        when(roomDAO.read(10L)).thenReturn(dummyRoomEntity);

        // Erstelle ein modifizierbares HotelEntity, das den Room enthält
        // Wir verwenden hier eine ArrayList, damit das Entfernen funktioniert.
        HotelEntity hotelEntity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withRooms(new ArrayList<>(Collections.singletonList(dummyRoomEntity)))
                .build();

        // Stub: dummyRoomEntity.getHotel() gibt das hotelEntity zurück
        when(dummyRoomEntity.getHotel()).thenReturn(hotelEntity);

        // Stub: hotelDAO.findById(1L) gibt das hotelEntity zurück
        when(hotelDAO.findById(1L)).thenReturn(hotelEntity);

        // Stub: hotelDAO.updateHotel(...) gibt das hotelEntity zurück (wichtig für den Update-Aufruf)
        when(hotelDAO.updateHotel(any(HotelEntity.class))).thenReturn(hotelEntity);

        // Act & Assert: Es sollte keine Exception geworfen werden
        assertDoesNotThrow(() -> adapter.removeRoom(10L));

        // Verifiziere, dass updateHotel() genau einmal aufgerufen wurde.
        verify(hotelDAO, times(1)).updateHotel(any(HotelEntity.class));

        // Zusätzlich: Überprüfe, dass dummyRoomEntity aus der Hotel-List entfernt wurde.
        assertFalse(hotelEntity.getRooms().contains(dummyRoomEntity));
    }






    @Test
    public void testUpdateRoom() {
        when(roomDAO.read(dummyRoom.getId())).thenReturn(dummyRoomEntity);
        // Simuliere, dass der Mapper die Änderungen zurückliefert
        doNothing().when(roomDAO).update(dummyRoomEntity);
        adapter.updateRoom(dummyRoom);
        verify(roomDAO, times(1)).update(dummyRoomEntity);
    }
}

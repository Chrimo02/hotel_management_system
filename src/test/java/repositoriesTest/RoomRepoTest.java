package repositoriesTest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.RoomIdentifier;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.RoomDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomIdentifierEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.BookingMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.RoomIdentifierMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.RoomMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters.RoomDatabaseAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class RoomRepoTest {
    @Mock
    private RoomDAO roomDAO;

    @Mock
    private RoomMapper roomMapper;

    @Mock
    private RoomIdentifierMapper roomIdentifierMapper;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private RoomDatabaseAdapter roomDatabaseAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindRoomById() {
        long roomId = 1L;
        RoomEntity roomEntity = mock(RoomEntity.class);
        Room room = mock(Room.class);

        when(roomDAO.read(roomId)).thenReturn(roomEntity);
        when(roomMapper.entityToDomain(roomEntity)).thenReturn(room);

        Room result = roomDatabaseAdapter.findRoomById(roomId);

        assertNotNull(result);
        verify(roomDAO).read(roomId);
        verify(roomMapper).entityToDomain(roomEntity);
    }

    @Test
    void testSaveRoom() {
        Room room = mock(Room.class);
        RoomEntity roomEntity = mock(RoomEntity.class);

        when(roomMapper.domainToEntity(room)).thenReturn(roomEntity);

        roomDatabaseAdapter.saveRoom(room);

        verify(roomMapper).domainToEntity(room);
        verify(roomDAO).create(roomEntity);
    }

    @Test
    void testRemoveRoom() {
        long roomId = 1L;
        RoomEntity roomEntity = mock(RoomEntity.class);

        when(roomDAO.read(roomId)).thenReturn(roomEntity);

        roomDatabaseAdapter.removeRoom(roomId);

        verify(roomDAO).read(roomId);
        verify(roomDAO).delete(roomEntity);
    }

    @Test
    void testUpdatePrice() {
        long roomId = 1L;
        double newPrice = 250.0;
        RoomEntity roomEntity = mock(RoomEntity.class);

        when(roomDAO.read(roomId)).thenReturn(roomEntity);

        roomDatabaseAdapter.updatePrice(roomId, newPrice);

        verify(roomDAO).read(roomId);
        verify(roomEntity).setPricePerNight(newPrice);
        verify(roomDAO).update(roomEntity);
    }

    @Test
    void testUpdateRoom() {
        Room room = mock(Room.class);
        RoomEntity roomEntity = mock(RoomEntity.class);
        RoomIdentifier roomIdentifier = mock(RoomIdentifier.class);
        RoomIdentifierEntity roomIdentifierEntity = mock(RoomIdentifierEntity.class);

        when(room.getId()).thenReturn(1L);
        when(roomDAO.read(1L)).thenReturn(roomEntity);
        when(room.getPricePerNight()).thenReturn(300.0);
        when(room.getRoomIdentifier()).thenReturn(roomIdentifier);
        when(roomIdentifierMapper.domainToEntity(roomIdentifier)).thenReturn(roomIdentifierEntity);

        roomDatabaseAdapter.updateRoom(room);

        verify(roomDAO).read(1L);
        verify(roomEntity).setPricePerNight(300.0);
        verify(roomEntity).setRoomIdentifier(roomIdentifierEntity);
        verify(roomEntity).setBookings(anySet());
        verify(roomDAO).update(roomEntity);
    }
}